package com.myretail.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myretail.dao.ProductRepository;
import com.myretail.domain.Price;
import com.myretail.domain.Product;
import com.myretail.service.ProductManagerService;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {
	
	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductController productController;
    
    @MockBean
    private ProductManagerService productManagerService;
    
    @MockBean
    private ProductRepository productRepository;
    
    private Price originalPrice  = null;
    private Product originalProduct = null;
    
    @Before
    public void setUp() {
    	originalPrice = new Price(58.99, "USD");
    	originalProduct = new Product("001", "Head Set 120S", originalPrice);
    }
    
    @Test
    public void testGetProductInformation() throws Exception {
        given(productController.getProductInformation(originalProduct.getId())).willReturn(ResponseEntity.ok(originalProduct));

        this.mockMvc.perform(get("/product/" + originalProduct.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(originalProduct.getName()))
                .andExpect(jsonPath("$.current_price.value").value(originalPrice.getValue()))
                .andExpect(jsonPath("$.current_price.currency_code").value(originalPrice.getCurrency_code()));
    }
    
    @Test
    public void updatePrice() throws Exception {
    	Price newPrice = new Price(580.99, "INR");
    	Product updatedProduct = new Product("001", "Head Set 120S", newPrice);
    	
    	given(productController.updateProductPrice(originalProduct.getId(), newPrice)).willReturn(ResponseEntity.ok(updatedProduct));
    	 
    	this.mockMvc.perform(put("/product/" + originalProduct.getId()).contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(newPrice)))
    				.andExpect(status().isOk())
    				.andExpect(jsonPath("$.name").value(originalProduct.getName()))
    				.andExpect(jsonPath("$.current_price.value").value(updatedProduct.getCurrent_price().getValue()))
    				.andExpect(jsonPath("$.current_price.currency_code").value(updatedProduct.getCurrent_price().getCurrency_code()));
    }
}