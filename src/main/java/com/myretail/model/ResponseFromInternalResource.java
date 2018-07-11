package com.myretail.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseFromInternalResource {
	ProductInfo product;
	
	public ResponseFromInternalResource() {
		super();
	}

	public ResponseFromInternalResource(ProductInfo product) {
		super();
		this.product = product;
	}

	public ProductInfo getProduct() {
		return product;
	}

	public void setProduct(ProductInfo product) {
		this.product = product;
	}
}