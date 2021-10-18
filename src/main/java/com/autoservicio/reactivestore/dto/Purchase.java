package com.autoservicio.reactivestore.dto;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import com.autoservicio.reactivestore.models.PurchasedProduct;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
@Document(collection="sale")
public class Purchase {
	@Id
	private String _id;
	@Field(targetType = FieldType.DOUBLE)
	private Double totalPurchasePrice;
	@Field(targetType = FieldType.DOUBLE)
	private Double totalSellingPrice;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date purchaseDate;
	private String client;
	@Field(targetType = FieldType.DOUBLE)
	private Double cardPaymentTax;
	private List<PurchasedProduct> items;

}
