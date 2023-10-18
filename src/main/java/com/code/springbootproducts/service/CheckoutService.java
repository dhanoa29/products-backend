package com.code.springbootproducts.service;

import com.code.springbootproducts.dto.Purchase;
import com.code.springbootproducts.dto.PurchaseResponse;

public interface CheckoutService {
	
	PurchaseResponse placeOrder(Purchase purchase);

}
