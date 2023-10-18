package com.code.springbootproducts.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.code.springbootproducts.entity.Country;
import com.code.springbootproducts.entity.Order;
import com.code.springbootproducts.entity.Product;
import com.code.springbootproducts.entity.ProductCategory;
import com.code.springbootproducts.entity.State;

import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

	@Value("${allowed.origins}")
	private String[] theAllowedOrigins;

	private EntityManager entityManager;

	@Autowired
	public MyDataRestConfig(EntityManager theEntityManager) {
		this.entityManager = theEntityManager;
	}

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
		// RepositoryRestConfigurer.super.configureRepositoryRestConfiguration(config,
		// cors);

		HttpMethod[] theUnsupportedActions = { HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE, HttpMethod.PATCH };

		// Disable HTTP methods for Product, ProductCategory, Country, State: PUT, POST,
		// DELETE
		disableHttpMethods(config, Product.class, theUnsupportedActions);
		disableHttpMethods(config, ProductCategory.class, theUnsupportedActions);
		disableHttpMethods(config, Country.class, theUnsupportedActions);
		disableHttpMethods(config, State.class, theUnsupportedActions);
		disableHttpMethods(config, Order.class, theUnsupportedActions);

		// Call an internal helper method
		exposeIds(config);

		cors.addMapping(config.getBasePath() + "/**").allowedOrigins(theAllowedOrigins);
	}

	private void disableHttpMethods(RepositoryRestConfiguration config, Class<?> theClass, HttpMethod[] http) {
		config.getExposureConfiguration().forDomainType(theClass)
				.withItemExposure((metadata, httpMethods) -> httpMethods.disable(http))
				.withCollectionExposure((metadata, httpMethods) -> httpMethods.disable(http));
	}

	@SuppressWarnings("rawtypes")
	private void exposeIds(RepositoryRestConfiguration config) {
		Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();

		List<Class> entityClasses = new ArrayList<>();

		for (EntityType temp : entities) {
			entityClasses.add(temp.getJavaType());

			Class[] domainTypes = entityClasses.toArray(new Class[0]);

			config.exposeIdsFor(domainTypes);
		}
	}
}
