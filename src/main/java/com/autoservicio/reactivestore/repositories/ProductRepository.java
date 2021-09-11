package com.autoservicio.reactivestore.repositories;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.autoservicio.reactivestore.dto.Product;

import reactor.core.publisher.Flux;

public interface ProductRepository extends ReactiveMongoRepository<Product, String> {
	@Query("{'description':{$regex:?0,$options:'i'}}")
	Flux<Product>findCoincidences(String regex,Sort sort);

	@Query("{}")
	Flux<Product> findAllPage(PageRequest pr);
}
