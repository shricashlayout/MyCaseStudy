package com.myretail.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.myretail.domain.Price;
import com.myretail.domain.Product;
import com.myretail.service.ProductManagerService;

@RestController
public class ProductController {

	@Autowired
	private ProductManagerService productManagerService;
	
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	@GetMapping(value="/product/{id}")
	public ResponseEntity<Product> getProductInformation(@PathVariable(name="id") String productId) {
		LOG.info("Insde getProductInformation for product :" + productId);
		return new ResponseEntity<Product>(productManagerService.getProduct(productId), HttpStatus.OK);
	}
	
	@PutMapping("/product/{id}")
	public ResponseEntity<Product> updateProductPrice(@PathVariable(name="id") String productId, @RequestBody @Valid Price price){
		LOG.info("Insde updateProductPrice for product :" + productId);
		return new ResponseEntity<Product>(productManagerService.upadteProductPrice(productId, price), HttpStatus.OK);
	}
	
}