package com.autoservicio.reactivestore.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Date;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	Logger logger=LoggerFactory.getLogger(SaleServiceImpl.class);;
	
	@Autowired
	SaleRepository saleRepository;
	@Autowired
	ReactiveMongoTemplate reactiveMongoTemplate;
	
	@Override
	public Flux<Purchase>getPurchasesInPeriod(String fechaInicio,String fechaFin){
		MatchOperation matchStage = Aggregation.match(getCriteriaInPeriod("purchaseDate",fechaInicio,fechaFin));
		ProjectionOperation projectStage = Aggregation.project().andExclude("items","_class");
		
		Aggregation aggregation = Aggregation.newAggregation(matchStage, projectStage);
		
		return reactiveMongoTemplate.aggregate(aggregation, "sale", Purchase.class);
	}
	
	@Override
	public Mono<Purchase>findSaleById(String id){
		return saleRepository.findById(Mono.just(id));
	}
	
	@Override
	public Flux<Purchase>findProductPurchasedInPeriod(String barcode,String startDate,String endDate){
		return saleRepository.findPurchasedProductInPeriod(getDateFromString(startDate+" 00:00:00"), getDateFromString(endDate+" 23:59:59"), barcode);
	}
	
	@Override
	public Flux<Document>findTotalSalePerDayInIsoWeek(String date){
		LocalDate startDate=LocalDate.parse(date);
		int isoDay=DayOfWeek.from(startDate).getValue();
		startDate=startDate.minusDays(isoDay-1);
		
		LocalDate endDate=startDate.plusDays(7);
		
		return saleRepository.findTotalSalePerDayInPeriod(startDate, endDate);
	}
	
	
	
	private Date getDateFromString(String dateString) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date=null;
		
		try {
			date = sdf.parse(dateString);
		} catch (ParseException e) {
			logger.error("Error parsing Date from String", e);
		}
		
		return date;
	}
	
	private Criteria getCriteriaInPeriod(String dbField,String startDate,String endDate) {
		Date dateInicio=getDateFromString(startDate+" 00:00:00");
		Date dateFin=getDateFromString(endDate+" 23:59:59");
		
		return Criteria.where(dbField).gte(dateInicio).lte(dateFin);
	}
	
}
