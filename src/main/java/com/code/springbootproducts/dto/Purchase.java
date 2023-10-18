package com.code.springbootproducts.dto;

import java.util.Set;

import com.code.springbootproducts.entity.Address;
import com.code.springbootproducts.entity.Customer;
import com.code.springbootproducts.entity.Order;
import com.code.springbootproducts.entity.OrderItem;

import lombok.Data;

@Data
public class Purchase {

	private Customer customer;
	
	private Address shippingAddress;
	
	private Address billingAddress;
	
	private Order order;
	
	private Set<OrderItem> orderItems;
	
}
