package com.code.springbootproducts.service;

import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.springbootproducts.dao.CustomerRepository;
import com.code.springbootproducts.dto.Purchase;
import com.code.springbootproducts.dto.PurchaseResponse;
import com.code.springbootproducts.entity.Customer;
import com.code.springbootproducts.entity.Order;
import com.code.springbootproducts.entity.OrderItem;

import jakarta.transaction.Transactional;

@Service
public class CheckoutServiceImpl implements CheckoutService {

	private CustomerRepository customerRepository;

	//@Autowired is optional here, because there is only one constructor
	@Autowired
	public CheckoutServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	@Transactional
	public PurchaseResponse placeOrder(Purchase purchase) {
		//Retrieve the order info from dto
		Order order = purchase.getOrder();
		
		//Generate tracking number
		String orderTrackingNumber = generateOrderTrackingNumber();
		order.setOrderTrackingNumber(orderTrackingNumber);
		
		//Populate order with order items
		Set<OrderItem> orderItems = purchase.getOrderItems();
		orderItems.forEach(item -> order.add(item));
		
		//Populate order with shipping address and billing address
		order.setShippingAddress(purchase.getShippingAddress());
		order.setBillingAddress(purchase.getBillingAddress());
		
		//Populate customer with order
		Customer customer = purchase.getCustomer();
		
		String email = customer.getEmail();
		
		
		Customer customerFromDb = customerRepository.findByEmail(email);
		
		if(customerFromDb != null) {
			customer = customerFromDb;
		}
		customer.add(order);
		
		//Save to database
		customerRepository.save(customer);
		
		return new PurchaseResponse(orderTrackingNumber);
	}

	private String generateOrderTrackingNumber() {
		return UUID.randomUUID().toString();
	}

}
