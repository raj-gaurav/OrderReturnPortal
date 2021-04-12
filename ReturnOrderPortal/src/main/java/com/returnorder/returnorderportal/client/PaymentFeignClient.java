package com.returnorder.returnorderportal.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value="payment-service",url="http://orderpay-env.eba-k38wafyn.us-east-2.elasticbeanstalk.com/processPayment")
public interface PaymentFeignClient {
	@GetMapping("/limit/{creditCard}")
	public long getLimit(@PathVariable("creditCard") String creditCardNumber);
}
