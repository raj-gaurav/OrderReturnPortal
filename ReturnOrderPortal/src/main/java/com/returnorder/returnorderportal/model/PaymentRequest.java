package com.returnorder.returnorderportal.model;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {
	@Pattern(regexp="^[0-9]*$", message="Field* should have only digits ")
	@Size(min=12, max=12, message="Field* should have exactly 12 digits")
	private String creditCardNumber;
	private String requestId;
	private long processingCharge;
	
}
