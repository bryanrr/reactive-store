package com.autoservicio.reactivestore.service;

import com.autoservicio.reactivestore.dto.Purchase;

import reactor.core.publisher.Flux;

public interface SaleService {
	Flux<Purchase>getPurchasesInPeriod(String fechaInicio,String fechaFin);
}
