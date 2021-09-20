package com.autoservicio.reactivestore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autoservicio.reactivestore.repositories.SaleRepository;
import com.autoservicio.reactivestore.service.SaleService;

@Service
public class SaleServiceImpl implements SaleService {
	@Autowired
	SaleRepository saleRepository;
	
}
