package com.autoservicio.reactivestore.controller;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.autoservicio.reactivestore.dto.Purchase;
import com.autoservicio.reactivestore.request.PurchaseDetails;
import com.autoservicio.reactivestore.request.PurchasesRequest;
import com.autoservicio.reactivestore.service.SaleService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class SaleRestController {
	@Autowired
	SaleService saleService;
	
	@RequestMapping(value="/purchases", method=RequestMethod.POST)
	public Flux<Purchase>getPurchasesPeriod(@RequestBody PurchasesRequest purchasesRequest){
		return saleService.getPurchasesInPeriod(purchasesRequest.getFechaInicio(), purchasesRequest.getFechaFin());
	}
	
	@RequestMapping(value="/purchase", method=RequestMethod.POST)
	public Mono<Purchase>getPurchaseById(@RequestBody PurchaseDetails purchaseDetails){
		return saleService.findSaleById(purchaseDetails.getPurchaseId());
	}
	
	@RequestMapping(value="/purchases/{barcode}", method=RequestMethod.POST)
	public Flux<Purchase>getPurchasedProductInPeriod(@PathVariable("barcode")String barcode,@RequestBody PurchasesRequest purchasesRequest){
		return saleService.findProductPurchasedInPeriod(barcode, purchasesRequest.getFechaInicio(), purchasesRequest.getFechaFin());
	}
	
	@RequestMapping(value="/total/isoweek/{date}", method=RequestMethod.GET)
	public Flux<Document>getTotalSalePerDayInIsoWeek(@PathVariable("date")String date){
		return saleService.findTotalSalePerDayInIsoWeek(date);
	}
	
	@RequestMapping(value="/purchases/add", method=RequestMethod.POST)
	public Mono<Purchase>addPurchase(@RequestBody Purchase purchase){
		return saleService.insertPurchase(purchase);
	}
	
}
