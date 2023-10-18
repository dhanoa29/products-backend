package com.code.springbootproducts.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.code.springbootproducts.entity.State;

@RepositoryRestResource(collectionResourceRel = "states", path = "states")
public interface StateRepository extends JpaRepository<State, Integer> {

	// http://localhost:8080/api/states/search/findByCountryCode?code=IN
	List<State> findByCountryCode(@Param("code") String code);

}
