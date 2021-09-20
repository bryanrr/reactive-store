package com.autoservicio.reactivestore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.autoservicio.reactivestore.service.SaleService;

@RestController
public class SaleRestController {
	@Autowired
	SaleService saleService;
	
}
