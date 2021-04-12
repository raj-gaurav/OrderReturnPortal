package com.returnorder.returnorderportal.controller;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.returnorder.returnorderportal.client.AuthenticationFeignClient;
import com.returnorder.returnorderportal.client.ComponentProcessingFeignClient;
import com.returnorder.returnorderportal.client.PaymentFeignClient;
import com.returnorder.returnorderportal.controller.LoginController;
import com.returnorder.returnorderportal.model.JwtResponse;
import com.returnorder.returnorderportal.model.PaymentRequest;
import com.returnorder.returnorderportal.model.ProcessRequest;
import com.returnorder.returnorderportal.model.ProcessResponse;
import com.returnorder.returnorderportal.model.UserModel;

class LoginControllerTest {

	private LoginController loginController;
	
	@Mock
	private PaymentFeignClient paymentFeignClient;
	
	@Mock
	private ComponentProcessingFeignClient componentProcessingFeignClient;
	
	@Mock
	private AuthenticationFeignClient authenticationFeignClient;
	
	private MockMvc mockMvc;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		loginController = new LoginController(componentProcessingFeignClient,authenticationFeignClient,paymentFeignClient);
		mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
	}

	@Test
	void testShowLogin() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.get("/login").accept(MediaType.ALL))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	void testShowLoginSNotNull() throws Exception {
		loginController.setS("abcd");
		mockMvc.perform(MockMvcRequestBuilders.get("/login").accept(MediaType.ALL))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	void testTestLoginRightUserPass() throws Exception {
		UserModel user = new UserModel("UID01G","Gaurav","Gaurav123");
		loginController.setS("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJHYXVyYXYiLCJleHAiOjE2MTgxNjcwNDMsImlhdCI6MTYxODEzMTA0M30.jzLAJ3i-BktOH88zdZIDv_f3VJZqd2MCMPqvioXk8zU");
		JwtResponse jwtResponse = new JwtResponse("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJHYXVyYXYiLCJleHAiOjE2MTgxNjcwNDMsImlhdCI6MTYxODEzMTA0M30.jzLAJ3i-BktOH88zdZIDv_f3VJZqd2MCMPqvioXk8zU", user);
		Mockito.when(authenticationFeignClient.getjwtResponse(Mockito.any())).thenReturn(jwtResponse);
		mockMvc.perform(MockMvcRequestBuilders.post("/login").param("user", "Gaurav").param("pass", "Gaurav123"))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	void testTestLoginWrongUserPass() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.post("/login").param("user", "Gaurav").param("pass", "Gaurav1243"))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void testProcessDetailHasError() throws Exception {
		
		loginController.setS("abcd");
		ProcessRequest processRequest = new ProcessRequest("UID","Gaurav5","8587953804","123456789123","Accessory","Headphone",5L,"Urgent","");
		
		mockMvc.perform(MockMvcRequestBuilders.post("/processDetail").flashAttr("processRequest", processRequest))
		.andExpect(MockMvcResultMatchers.status().isOk());
		
	}
	
	@Test
	void testProcessDetailNoError() throws Exception {
		
		loginController.setS("abcd");
		ProcessRequest processRequest = new ProcessRequest("UID","Gaurav","8587953804","123456789123","Accessory","Headphone",5L,"Urgent","");
		ProcessResponse response = new ProcessResponse();
		Mockito.when(componentProcessingFeignClient.processResponse(Mockito.anyString(),Mockito.any())).thenReturn(response);
		mockMvc.perform(MockMvcRequestBuilders.post("/processDetail").flashAttr("processRequest", processRequest))
		.andExpect(MockMvcResultMatchers.status().isOk());
		
	}
	
	@Test
	void testProcessDetailSIsNull() throws Exception {
		
		loginController.setS(null);
		ProcessRequest processRequest = new ProcessRequest("UID","Gaurav5","8587953804","123456789123","Accessory","Headphone",5L,"Urgent","");
		
		mockMvc.perform(MockMvcRequestBuilders.post("/processDetail").flashAttr("processRequest", processRequest))
		.andExpect(MockMvcResultMatchers.status().isOk());
		
	}

	
	@Test
	void testPaymentSNull() throws Exception {
		loginController.setS(null);
		mockMvc.perform(MockMvcRequestBuilders.post("/payment"))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	void testPaymentSNotNull() throws Exception {
		loginController.setS("abcd");
		/*
		 * mockMvc.perform(MockMvcRequestBuilders.post("/payment"))
		 * .andExpect(MockMvcResultMatchers.status().isOk());
		 */
	}
	
	@Test
	void testValidateCardSNotNullHasError() throws Exception {
		loginController.setS("abcd");
		PaymentRequest request = new PaymentRequest("1234567891234","REQ1ASID123",1750L);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/validateCard").flashAttr("paymentPage", request))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	void testValidateCardSNotNullNoErrorWrongCreditCard() throws Exception {
		loginController.setS("abcd");
		PaymentRequest request = new PaymentRequest("123456789125","REQ1ASID123",1750L);
		
		//Mockito.when(paymentFeignClient.getLimit(Mockito.anyString())).thenReturn(50000L);
		//Mockito.when(componentProcessingFeignClient.completeProcessing(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyLong(), Mockito.anyLong())).thenReturn("success");
		
		mockMvc.perform(MockMvcRequestBuilders.post("/validateCard").flashAttr("paymentPage", request))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	
	@Test
	void testValidateCardSNotNullNoErrorSuccess() throws Exception {
		loginController.setS("abcd");
		PaymentRequest request = new PaymentRequest("123456789123","REQ1ASID123",1750L);
		
		Mockito.when(paymentFeignClient.getLimit(Mockito.anyString())).thenReturn(50000L);
		Mockito.when(componentProcessingFeignClient.completeProcessing(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyLong(), Mockito.anyLong())).thenReturn("success");
		
		mockMvc.perform(MockMvcRequestBuilders.post("/validateCard").flashAttr("paymentPage", request))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	void testValidateCardSNotNullNoErrorFailure() throws Exception {
		loginController.setS("abcd");
		PaymentRequest request = new PaymentRequest("123456789123","REQ1ASID123",1750L);
		
		Mockito.when(paymentFeignClient.getLimit(Mockito.anyString())).thenReturn(50000L);
		Mockito.when(componentProcessingFeignClient.completeProcessing(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyLong(), Mockito.anyLong())).thenReturn("failure");
		
		mockMvc.perform(MockMvcRequestBuilders.post("/validateCard").flashAttr("paymentPage", request))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	void testValidateCardSNotNullNoError() throws Exception {
		loginController.setS("abcd");
		PaymentRequest request = new PaymentRequest("123456789123","REQ1ASID123",1750L);
		
		Mockito.when(paymentFeignClient.getLimit(Mockito.anyString())).thenReturn(50000L);
		Mockito.when(componentProcessingFeignClient.completeProcessing(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyLong(), Mockito.anyLong())).thenReturn("error");
		
		mockMvc.perform(MockMvcRequestBuilders.post("/validateCard").flashAttr("paymentPage", request))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	

	@Test
	void testValidateCardSNull() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/validateCard"))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void testLogout() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/logout"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

}
