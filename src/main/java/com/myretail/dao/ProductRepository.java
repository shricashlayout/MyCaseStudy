package com.myretail.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.myretail.domain.Product;

public interface ProductRepository  extends MongoRepository<Product, String>{

}
