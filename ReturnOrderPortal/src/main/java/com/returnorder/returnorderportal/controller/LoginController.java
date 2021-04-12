package com.returnorder.returnorderportal.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.returnorder.returnorderportal.client.AuthenticationFeignClient;
import com.returnorder.returnorderportal.client.ComponentProcessingFeignClient;
import com.returnorder.returnorderportal.client.PaymentFeignClient;
import com.returnorder.returnorderportal.model.JwtRequest;
import com.returnorder.returnorderportal.model.JwtResponse;
import com.returnorder.returnorderportal.model.PaymentRequest;
import com.returnorder.returnorderportal.model.ProcessRequest;
import com.returnorder.returnorderportal.model.ProcessResponse;

import io.swagger.annotations.ApiOperation;

@Controller
@SessionAttributes("session")
public class LoginController {

	private Logger log = LoggerFactory.getLogger(LoginController.class);

	private String token;
	private String s;
	private ProcessRequest request;
	private ProcessResponse response;
	private String creditCard;
	private JwtRequest jwtRequest;
	private JwtResponse jwtResponse;

	@Autowired
	private PaymentFeignClient paymentFeignClient;

	@Autowired
	private ComponentProcessingFeignClient componentProcessingFeignClient;

	@Autowired
	private AuthenticationFeignClient authenticationFeignClient;

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}

	public LoginController(ComponentProcessingFeignClient componentProcessingFeignClient2,
			AuthenticationFeignClient authenticationFeignClient2, PaymentFeignClient paymentFeignClient2) {
		this.componentProcessingFeignClient = componentProcessingFeignClient2;
		this.authenticationFeignClient = authenticationFeignClient2;
		this.paymentFeignClient = paymentFeignClient2;

	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	@ApiOperation(value = "Show the Login Page", notes = "This mapping shows the login page.")
	public String showLogin(ModelMap model) {
		log.debug("showLogin s : {}", getS());

		if (getS() == null)
			return "loginPage";
		else {
			model.addAttribute("processRequest", new ProcessRequest());
			return "homePage";
		}

	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ApiOperation(value = "Test Login", notes = "Communicate with JwtAuthentication Microservice - This mapping authenticate the users and if that is valid it navigates to homepage else again shoe the login page with error message.")
	public String testLogin(ModelMap model, @RequestParam("user") String username,
			@RequestParam("pass") String password) {

		try {

			jwtRequest = new JwtRequest(username, password);

			jwtResponse = authenticationFeignClient.getjwtResponse(jwtRequest);
			model.put("userId", jwtResponse.getUserModel().getUserId());
			log.debug("userId : {}", model.get("userId"));
			token = jwtResponse.getToken();
			log.debug("{}", token);
			model.put("session", token);
			setS((String) model.get("session"));
			// if (getS() != null) {
			model.addAttribute("processRequest", new ProcessRequest());
			return "homePage";
			/*
			 * } else { return "loginPage"; }
			 */
		} catch (Exception e) {
			boolean active = true;
			model.put("error", active);
			return "loginPage";
		}

	}

	@RequestMapping(value = "/processDetail", method = RequestMethod.POST)
	@ApiOperation(value = "Process the Details", notes = "Communicate with ComponentProcessing Microservice and process the details and then navigates to process Order page")
	public String processDetail(ModelMap model, @ModelAttribute("processRequest") @Valid ProcessRequest processRequest,
			BindingResult result) {
		request = processRequest;

		if (getS() != null) {

			if (result.hasErrors())
				return "homePage";
			log.debug("processRequest : {}", processRequest);

			creditCard = processRequest.getCreditCardNumber();
			model.put("creditCard", creditCard);

			log.debug("CreditCardNumber :: {}", model.get("creditCard"));

			response = componentProcessingFeignClient.processResponse(token, processRequest);

			log.debug("Process Response : {}", response);
			if (response != null) {
				model.put("response", response);

			}

			return "processOrder";
		} else
			return "loginPage";
	}

	@RequestMapping(value = "/payment", method = RequestMethod.POST)
	@ApiOperation(value = "Payment Mapping", notes = "After confimation of the order this mapping navigates the user to the payment page")
	public String payment(ModelMap model) {

		if (getS() != null) {

			model.addAttribute("paymentPage", new PaymentRequest());

			log.debug("processresponse : {}", response);
			model.put("processresponse", response);
			model.put("card", creditCard);
			return "payment";
		}

		else
			return "loginPage";
	}

	@RequestMapping(value = "/validateCard", method = RequestMethod.POST)
	@ApiOperation(value = "Payment Validaation", notes = "After confirming the payment this mapping communicates with payment microservice and component processing microservice and according give the result and navigates to the respective page.")
	public String validateCard(ModelMap map, @ModelAttribute("paymentPage") @Valid PaymentRequest paymentRequest,
			BindingResult result) {

		if (getS() != null) {
			map.put("request", request);
			map.put("processresponse", response);

			if (result.hasErrors()) {
				boolean valid = true;
				map.put("valid", valid);
				return "payment";
			}

			long creditLimit = 0L;
			log.debug("validateCard creditCardNumber :: {}", paymentRequest.getCreditCardNumber());

			try {
				creditLimit = paymentFeignClient.getLimit(paymentRequest.getCreditCardNumber());
			} catch (Exception e) {
				boolean valid = true;
				map.put("valid", valid);
				// map.put("processresponse",response);
				return "payment";
			}
			log.debug("creditLimit : {}", creditLimit);

			try {

				String str = componentProcessingFeignClient.completeProcessing(token, paymentRequest.getRequestId(),
						paymentRequest.getCreditCardNumber(), creditLimit, paymentRequest.getProcessingCharge());
				log.debug("string : {}", str);

				map.put("paymentRequest", paymentRequest);
				map.put("user", jwtRequest.getUsername());
				if (str.equals("success")) {
					return "success";
				} else if (str.equals("failure")) {
					return "fail";
				} else {
					boolean valid = true;
					map.put("valid", valid);
					map.put("processresponse", response);
					return "payment";
				}
			} catch (Exception e) {
				boolean valid = true;
				map.put("valid", valid);
				// map.put("processresponse",response);
				return "payment";
			}
		} else {
			// log.debug("string : {}",string);
			return "loginPage";
		}

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	@ApiOperation(value = "Logout Mapping", notes = "This mapping vacates the session variable and navigates the user to the login page")
	public String logout() {
		setS(null);
		return "loginPage";
	}

}
