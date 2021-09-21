package com.autoservicio.reactivestore.service;

import com.autoservicio.reactivestore.dto.Purchase;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SaleService {
	Flux<Purchase>getPurchasesInPeriod(String fechaInicio,String fechaFin);
	Mono<Purchase>findSaleById(String id);
}
