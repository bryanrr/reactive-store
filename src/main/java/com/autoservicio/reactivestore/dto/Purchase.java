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

	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public Double getTotalPurchasePrice() {
		return totalPurchasePrice;
	}
	public void setTotalPurchasePrice(Double totalPurchasePrice) {
		this.totalPurchasePrice = totalPurchasePrice;
	}
	public Double getTotalSellingPrice() {
		return totalSellingPrice;
	}
	public void setTotalSellingPrice(Double totalSellingPrice) {
		this.totalSellingPrice = totalSellingPrice;
	}
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public Double getCardPaymentTax() {
		return cardPaymentTax;
	}
	public void setCardPaymentTax(Double cardPaymentTax) {
		this.cardPaymentTax = cardPaymentTax;
	}
	public List<PurchasedProduct> getItems() {
		return items;
	}
	public void setItems(List<PurchasedProduct> items) {
		this.items = items;
	}
}
