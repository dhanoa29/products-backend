package com.code.springbootproducts.dto;

import lombok.Data;

@Data
public class PurchaseResponse {
	
	//Lombok will create a constructor for final fields
	private final String orderTrackingNumber;

//	public PurchaseResponse(String orderTrackingNumber) {
//		this.orderTrackingNumber = orderTrackingNumber;
//	}

}
