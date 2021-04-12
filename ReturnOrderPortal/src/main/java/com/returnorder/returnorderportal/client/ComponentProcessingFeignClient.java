package com.returnorder.returnorderportal.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.returnorder.returnorderportal.model.ProcessRequest;
import com.returnorder.returnorderportal.model.ProcessResponse;

@FeignClient(value="component-processing-service",url="http://ordercomponentprocess-env.eba-dmzamjzm.us-east-2.elasticbeanstalk.com")
public interface ComponentProcessingFeignClient {
	
	@PostMapping("/completeProcessing/{requestId}/{creditCard}/{limit}/{charge}") //To be changed....to PostMapping
	String completeProcessing(@RequestHeader("token")String token,@PathVariable("requestId") String requestId, @PathVariable("creditCard") String creditCardNumber,@PathVariable("limit") long creditLimit, @PathVariable("charge") long processingCharge);
		
	@PostMapping("/processDetail")
	ProcessResponse processResponse(@RequestHeader("token")String token,@RequestBody ProcessRequest processRequest);
}
