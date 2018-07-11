package com.myretail.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductInfo {
	Item item;

	public ProductInfo() {
		super();
	}
	
	public ProductInfo(Item item) {
		super();
		this.item = item;
	}
	
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
}