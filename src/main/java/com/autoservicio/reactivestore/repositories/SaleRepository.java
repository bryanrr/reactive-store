package com.autoservicio.reactivestore.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.autoservicio.reactivestore.dto.Purchase;

public interface SaleRepository extends ReactiveMongoRepository<Purchase, String> {

}
