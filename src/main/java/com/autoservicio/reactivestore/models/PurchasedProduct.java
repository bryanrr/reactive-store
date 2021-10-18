package com.autoservicio.reactivestore.models;

import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class PurchasedProduct {
	private String barcode;
	private String description;
	@Field(targetType = FieldType.DOUBLE)
	private Double quantity;
	@Field(targetType = FieldType.DOUBLE)
	private Double purchasePrice;
	@Field(targetType = FieldType.DOUBLE)
	private Double sellingPrice;
	
}
