package com.autoservicio.reactivestore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.autoservicio.reactivestore.dto.Purchase;
import com.autoservicio.reactivestore.request.PurchasesRequest;
import com.autoservicio.reactivestore.service.SaleService;

import reactor.core.publisher.Flux;

@RestController
public class SaleRestController {
	@Autowired
	SaleService saleService;
	
	@RequestMapping(value="/purchases", method=RequestMethod.POST)
	public Flux<Purchase>getPurchasesPeriod(@RequestBody PurchasesRequest purchasesRequest){
		return saleService.getPurchasesInPeriod(purchasesRequest.getFechaInicio(), purchasesRequest.getFechaFin());
	}
	
}
