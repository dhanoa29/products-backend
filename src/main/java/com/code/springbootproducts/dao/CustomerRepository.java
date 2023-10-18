package com.code.springbootproducts.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.code.springbootproducts.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	Customer findByEmail(String theEmail);
}
