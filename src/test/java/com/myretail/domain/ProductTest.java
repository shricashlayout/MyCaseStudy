package com.myretail.domain;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ProductTest {
	
	private Product product1 = null;
	private Product product2 =  null;
	
	@Before
	public void setUp() {
		product1 = new Product("101","TV",new Price(100.00, "USD"));
		product2 = new Product("101","TV",new Price(100.00, "USD"));
	}
	
	@Test
	public void testProductsWithSameProductId() {
		assertTrue(product1.equals(product2));
	}

	@Test
	public void pricesWithSameValueAndCurrencyCodeHaveSameHashCode() {
		assertTrue(product1.hashCode()==product2.hashCode());
	}
}
