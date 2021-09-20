package com.autoservicio.reactivestore.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
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
	@Autowired
	ReactiveMongoTemplate reactiveMongoTemplate;
	
	@Override
	public Flux<Product>getAllProducts(){
		return productRepository.findAll();
	}
	
	@Override
	public Mono<Product>getProductById(String id){
		return productRepository.findById(id);
	}
	
	@Override
	public Flux<Product>getProductCoincidences(String coincidences){
		String[]words=coincidences.split(" ");
		String regexp="[\\w]*";
		
		for(String word:words) {
			regexp+=word+"[\\w\\s]*";
		}
		
		Sort sort=Sort.by(Sort.Direction.ASC, "description");
		
		return productRepository.findCoincidences(regexp,sort);
	}
	
	@Override
	public Flux<Product>getAllProductsPage(int page,int records){
		Sort sort=Sort.by(Sort.Direction.ASC, "description");
		
		return productRepository.findAllPage(PageRequest.of(page, records, sort));
	}
	
	@Override
	public Mono<Product> updateProductPrices(Product product){
		Query query=new Query();
		query.addCriteria(Criteria.where("_id").is(product.getId()));
		
		Update update=new Update();
		update.set("sellingPrice", product.getSellingPrice());
		update.set("purchasePrice", product.getPurchasePrice());
		update.set("lastUpdatedTime", new Date());
		
		return reactiveMongoTemplate.findAndModify(query, update, Product.class);
		
	}

}

