package com.myretail.service.impl;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.myretail.dao.ProductRepository;
import com.myretail.domain.Product;
import com.myretail.errorhandler.ProductExceptionHandler;
import com.myretail.domain.Price;
import com.myretail.exception.ProductNotFoundException;
import com.myretail.model.ResponseFromInternalResource;
import com.myretail.service.ProductManagerService;

import static com.myretail.constants.MyRetailAppConstants.*;

@Service
public class ProductManagerServiceImpl implements ProductManagerService {
	
	private ProductRepository productRepository;
	
	private RestTemplate restTemplate ;
	
	@Autowired
	public ProductManagerServiceImpl(ProductRepository productRepository) {
		super();
		this.productRepository = productRepository;
		this.restTemplate = new RestTemplate();
		
	}
	public void setRestTempate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	@Override
	public String getProductNameFromInternalResource(String productId) throws ProductNotFoundException{
		this.restTemplate.setErrorHandler(new ProductExceptionHandler());
		ResponseFromInternalResource response  = restTemplate.getForObject(API_URL + productId + API_PARAM, ResponseFromInternalResource.class);
		String productName = response.getProduct().getItem().getProduct_description().getTitle();
		return productName ;
	}

	@Override
	public Product getProductById(String productId) throws ProductNotFoundException{
		Product product=null;
		try {
			product = productRepository.findById(productId).get();
		}catch(NoSuchElementException ex) {
			throw new ProductNotFoundException(PRODUCT_NOT_IN_DB);
		}
		return product;
	}

	@Override
	public Product getProduct(String productId) {
		Product product = getProductById(productId);
		product.setName(getProductNameFromInternalResource(productId));
		return product;
	}

	@Override
	public Product upadteProductPrice(String productId, Price price) throws ProductNotFoundException {
		Product product = getProductById(productId);
		product.setCurrent_price(price);
		return productRepository.save(product);
	}

}