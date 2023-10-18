package com.code.springbootproducts.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.code.springbootproducts.entity.Product;

@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product, Long> {
	// It will expose
	// "http://localhost:4200/api/products/search/findByCategoryId?id=2"
	// assuming id passed is 2
	Page<Product> findByCategoryId(@Param("id") Long id, Pageable pageable);

	Page<Product> findByNameContaining(@Param("name") String name, Pageable pageable);

//	Page<Product> findById(@Param("id") Long id, Pageable pageable);
}
