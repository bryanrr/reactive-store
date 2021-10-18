package com.autoservicio.reactivestore.service;

import com.autoservicio.reactivestore.dto.Product;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {
	Flux<Product>getAllProducts();
	Mono<Product>getProductById(String id);
	Flux<Product>getProductCoincidences(String coincidences);
	Flux<Product>getAllProductsPage(int page,int records);
	Mono<Product> updateProductPrices(Product product);
}
