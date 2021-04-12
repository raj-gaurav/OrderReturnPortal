package com.returnorder.returnorderportal.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.returnorder.returnorderportal.model.JwtRequest;
import com.returnorder.returnorderportal.model.JwtResponse;

@FeignClient(value="jwt-service",url="http://orderjwtauthentication-env.eba-8y3qevhm.us-east-2.elasticbeanstalk.com")
public interface AuthenticationFeignClient {

	@RequestMapping("/token")
	JwtResponse getjwtResponse(@RequestBody JwtRequest jwtRequest);
	
	
	
}
