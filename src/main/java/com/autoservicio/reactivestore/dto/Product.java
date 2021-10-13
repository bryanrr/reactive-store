package com.autoservicio.reactivestore.dto;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import com.autoservicio.reactivestore.models.Distributor;

@Document(collection="product")
public class Product implements Comparable<Product>{
	@Id
	private String id;
	private String description;
	@Field(targetType = FieldType.DOUBLE)
	private BigDecimal purchasePrice;
	@Field(targetType = FieldType.DOUBLE)
	private BigDecimal sellingPrice;
	@Field(targetType = FieldType.DOUBLE)
	private Double stock;
	@Field(targetType = FieldType.BOOLEAN)
	private Boolean fractional;
	@Field(targetType = FieldType.BOOLEAN)
	private Boolean promotion;
	private Date lastUpdatedTime;
	private Distributor distributor;
	
	public void decreaseStock(Double decrease) {
		this.stock-= decrease;
	}
	
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
	public Double getStock() {
		return stock;
	}
	public void setStock(Double stock) {
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
	public Date getLastUpdatedTime() {
		return lastUpdatedTime;
	}
	public void setLastUpdatedTime(Date lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
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
	@Override
	public int compareTo(Product p) {
		return id.compareToIgnoreCase(p.getId());
	}
}
