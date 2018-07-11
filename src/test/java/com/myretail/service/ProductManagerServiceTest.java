package com.myretail.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.myretail.dao.ProductRepository;
import com.myretail.domain.Price;
import com.myretail.domain.Product;
import com.myretail.exception.ProductNotFoundException;
import com.myretail.model.Item;
import com.myretail.model.ProductDescription;
import com.myretail.model.ProductInfo;
import com.myretail.model.ResponseFromInternalResource;
import com.myretail.service.impl.ProductManagerServiceImpl;

import static org.mockito.Mockito.when;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;;


@RunWith(SpringRunner.class)
public class ProductManagerServiceTest {

	private ProductManagerServiceImpl productManagerService;
	
	@MockBean
	private ProductRepository productRepository;
	
	@MockBean
	private RestTemplate restTemplate;
	
	private Product expectedProduct ;
	private ResponseFromInternalResource response ;
	
	@SuppressWarnings("unchecked")
	@Before
	public void setUp() {
		response = new ResponseFromInternalResource(new ProductInfo(new Item(new ProductDescription("SAMSUNG 9S"))) );
		productManagerService = new ProductManagerServiceImpl(productRepository);
		productManagerService.setRestTempate(restTemplate);
		
		when(restTemplate.getForObject(anyString(), any(Class.class))).thenReturn(response);
		
		expectedProduct = new Product("123A", null, new Price(100.12, "USD"));
		when(productRepository.findById(expectedProduct.getId())).thenReturn(Optional.ofNullable(expectedProduct));
	}
	
	@Test
	public void givenProductIdThenReturnProductName() {
		assertEquals("SAMSUNG 9S",productManagerService.getProductNameFromInternalResource(anyString()));
	}
	
	@Test(expected= ProductNotFoundException.class)
	@SuppressWarnings("unchecked")
	public void testForNotFoundInInternalSource() {
		when(restTemplate.getForObject(anyString(), any(Class.class))).thenThrow(ProductNotFoundException.class);
		productManagerService.getProductNameFromInternalResource(anyString());
	}
	
	@Test
	public void testForGettingProductFromDatabase() {
		Product actualProduct = productManagerService.getProductById(expectedProduct.getId());
		assertNotNull(actualProduct);
		assertEquals("123A", actualProduct.getId());
		assertNull(actualProduct.getName());
		assertEquals("USD", actualProduct.getCurrent_price().getCurrency_code());
	}
	
		
	@Test(expected= ProductNotFoundException.class)
	public void testForProductNotFoundExceptionInDatabase() {
		when(productRepository.findById(anyString())).thenThrow(ProductNotFoundException.class);
		productManagerService.getProductById(anyString());
	}

	@Test
	public void testForDetailedProductInformationForGivenProduct() {
		Product actualProduct = productManagerService.getProduct(expectedProduct.getId());
		assertEquals("123A", actualProduct.getId());
		assertNotNull(actualProduct.getName());
		assertEquals("SAMSUNG 9S", actualProduct.getName());
		assertEquals("USD", actualProduct.getCurrent_price().getCurrency_code());
	} 
}