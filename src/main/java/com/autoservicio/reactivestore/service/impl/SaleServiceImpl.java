package com.autoservicio.reactivestore.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import com.autoservicio.reactivestore.dto.Purchase;
import com.autoservicio.reactivestore.repositories.SaleRepository;
import com.autoservicio.reactivestore.service.SaleService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SaleServiceImpl implements SaleService {
	@Autowired
	SaleRepository saleRepository;
	@Autowired
	ReactiveMongoTemplate reactiveMongoTemplate;
	
	@Override
	public Flux<Purchase>getPurchasesInPeriod(String fechaInicio,String fechaFin){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date dateInicio=null;
		Date dateFin=null;
		try {
			dateInicio = sdf.parse(fechaInicio+" 00:00:00");
			dateFin=sdf.parse(fechaFin+" 23:59:59");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		MatchOperation matchStage = Aggregation.match(Criteria.where("purchaseDate").gte(dateInicio).lte(dateFin));
		ProjectionOperation projectStage = Aggregation.project().andExclude("items","_class");
		
		Aggregation aggregation = Aggregation.newAggregation(matchStage, projectStage);
		
		return reactiveMongoTemplate.aggregate(aggregation, "sale", Purchase.class);
	}
	
	@Override
	public Mono<Purchase>findSaleById(String id){
		return saleRepository.findById(Mono.just(id));
	}
	
}
