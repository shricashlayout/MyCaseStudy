package com.myretail.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {
	private ProductDescription product_description;

	public Item() {
		super();
	}

	public Item(ProductDescription product_description) {
		super();
		this.product_description = product_description;
	}

	public ProductDescription getProduct_description() {
		return product_description;
	}

	public void setProduct_description(ProductDescription product_description) {
		this.product_description = product_description;
	}
}