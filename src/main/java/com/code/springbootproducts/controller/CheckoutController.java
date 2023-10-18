package com.code.springbootproducts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code.springbootproducts.dto.Purchase;
import com.code.springbootproducts.dto.PurchaseResponse;
import com.code.springbootproducts.service.CheckoutService;

@RestController
@CrossOrigin
@RequestMapping("/api/checkout")
public class CheckoutController {
	
	private CheckoutService checkoutService;

	@Autowired
	public CheckoutController(CheckoutService checkoutService) {
		this.checkoutService = checkoutService;
	}
	
	@PostMapping("/purchase")
	public PurchaseResponse placeOrder(@RequestBody Purchase purchase) {
		return checkoutService.placeOrder(purchase);
	}
	
}