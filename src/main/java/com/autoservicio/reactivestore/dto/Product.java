package com.autoservicio.reactivestore.dto;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.autoservicio.reactivestore.models.Distributor;

@Document(collection="product")
public class Product {
	@Id
	private String id;
	private String description;
	private BigDecimal purchasePrice;
	private BigDecimal sellingPrice;
	private Number stock;
	private Boolean fractional;
	private Boolean promotion;
	private Distributor distributor;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getPurchasePrice() {
		return purchasePrice;
	}
	public void setPurchasePrice(BigDecimal purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	public BigDecimal getSellingPrice() {
		return sellingPrice;
	}
	public void setSellingPrice(BigDecimal sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	public Number getStock() {
		return stock;
	}
	public void setStock(Number stock) {
		this.stock = stock;
	}
	public Boolean getFractional() {
		return fractional;
	}
	public void setFractional(Boolean fractional) {
		this.fractional = fractional;
	}
	public Boolean getPromotion() {
		return promotion;
	}
	public void setPromotion(Boolean promotion) {
		this.promotion = promotion;
	}
	public Distributor getDistributor() {
		return distributor;
	}
	public void setDistributor(Distributor distributor) {
		this.distributor = distributor;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", description=" + description + ", purchasePrice=" + purchasePrice
				+ ", sellingPrice=" + sellingPrice + "]";
	}
}
