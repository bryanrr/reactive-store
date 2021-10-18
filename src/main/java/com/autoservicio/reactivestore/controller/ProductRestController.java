package com.autoservicio.reactivestore.controller;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.autoservicio.reactivestore.dto.Product;
import com.autoservicio.reactivestore.service.ProductService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ProductRestController {
	@Autowired
	ProductService productService;

	@RequestMapping(value="/products", method=RequestMethod.GET,produces=MediaType.APPLICATION_STREAM_JSON_VALUE)
	Flux<Product> getAllProductsAsDelayedStream(){
		return productService.getAllProducts().delayElements(Duration.ofSeconds(1));
	}
	
	@RequestMapping(value="/product/{id}", method=RequestMethod.GET)
	Mono<Product> getProductById(@PathVariable("id")String id){
		return productService.getProductById(id);
	}
	
	@RequestMapping(value="/products/{coincidences}", method=RequestMethod.GET)
	Flux<Product> getProductsByCoincidences(@PathVariable("coincidences")String coincidences){
		return productService.getProductCoincidences(coincidences);
	}
	
	@RequestMapping(value="/product/updateprice", method=RequestMethod.PUT)
	public Mono<Product> updateProductPrice(@RequestBody Product product){
		return productService.updateProductPrices(product);
	}
}
