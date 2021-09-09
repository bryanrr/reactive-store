package com.autoservicio.reactivestore.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.autoservicio.reactivestore.dto.Product;

public interface ProductRepository extends ReactiveMongoRepository<Product, String> {

}
