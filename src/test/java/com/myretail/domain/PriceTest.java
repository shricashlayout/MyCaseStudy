package com.myretail.domain;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.util.ClassUtils;

public class PriceTest {
	private Price price1;
	private Price price2;
	
	@Test
	public void testProductsWithSameProductId() {
		createPricesToTest();
		assertTrue(price1.equals(price2));
	}
	
	@Test
	public void testForNoArgConstructor() {
		assertTrue(ClassUtils.hasConstructor(Price.class));
	}
	
	@Test
	public void pricesWithSameValueAndCurrencyCodeHaveSameHashCode() {
		createPricesToTest();
		assertTrue(price1.hashCode()==price2.hashCode());
	}
	
	private void createPricesToTest() {
		price1 = new Price(100.00, "USD");
		price2 = new Price(100.00, "USD");
	}
}
