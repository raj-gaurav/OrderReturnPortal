package com.returnorder.returnorderportal.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
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
public class ProcessRequest {
	
	private String userId;
	@NotEmpty(message = "Field* can't be empty")
	@Pattern(regexp = "^[A-Za-z ]*$", message = "Field* should contain only Alphabets")
	private String name;
	@Pattern(regexp = "^[0-9]*$", message="Field* should have only digits")
	@Size(min=10, max=10,message="Contact number should have exactly 10 digits")
	private String contactNumber;
	@Pattern(regexp = "^[0-9]*$", message="Field* should have only digits")
	@Size(min=12, max=12,message="Credit Card number should have exactly 12 digits")
	private String creditCardNumber;
	private String componentType;
	@NotEmpty(message = "Field* can't be empty")
	private String componentName;
	@PositiveOrZero(message="Field* should have a positive value.")
	private long quantity;
	@NotEmpty(message = "Field* can't be empty")
	private String details;
	private String isPriorityRequest;
}
