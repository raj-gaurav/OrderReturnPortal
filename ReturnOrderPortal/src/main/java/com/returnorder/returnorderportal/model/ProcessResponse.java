package com.returnorder.returnorderportal.model;

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
public class ProcessResponse {
	
	private String requestId;
	private long processingCharge;
	private long packagingAndDeliveryCharge;
	private long total;
	private String dateOfDelivery;
	private String userId;
}
