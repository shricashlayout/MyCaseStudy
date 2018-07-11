package com.myretail.service;

import com.myretail.domain.Price;
import com.myretail.domain.Product;
import com.myretail.exception.ProductNotFoundException;

public interface ProductManagerService {
	public String getProductNameFromInternalResource(String productId) throws ProductNotFoundException;
	public Product getProductById(String productId) throws ProductNotFoundException;
	public Product getProduct(String productId) ;
	public Product upadteProductPrice(String productId, Price price) throws ProductNotFoundException;
}
