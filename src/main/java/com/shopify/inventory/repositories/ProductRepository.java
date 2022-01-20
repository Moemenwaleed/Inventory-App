package com.shopify.inventory.repositories;

import com.shopify.inventory.models.Product;
import org.springframework.data.repository.CrudRepository;

/**
 * ProductRepository is extended from CrudRepository, which holds the basic CRUD actions on any Entity.
 * Methods of this repository can be viewed at https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html
 */

public interface ProductRepository extends CrudRepository<Product, Long> {

}
