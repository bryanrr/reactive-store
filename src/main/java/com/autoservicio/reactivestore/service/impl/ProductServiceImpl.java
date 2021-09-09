package com.autoservicio.reactivestore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autoservicio.reactivestore.dto.Product;
import com.autoservicio.reactivestore.repositories.ProductRepository;
import com.autoservicio.reactivestore.service.ProductService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductRepository productRepository;
	
	@Override
	public Flux<Product>getAllProducts(){
		return productRepository.findAll();
	}
	
	@Override
	public Mono<Product>getProductById(String id){
		return productRepository.findById(id);
	}

}
