package com.myretail.constants;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static com.myretail.constants.MyRetailAppConstants.*;

public class MyRetailAppConstantsTests {
	
	@Test
	public void testToCheckCorrectErrorVerbiage() {
		assertEquals("Product not present in Data Base.", PRODUCT_NOT_IN_DB);
		assertEquals("Product not present.", PRODUCT_NOT_FOUND);
		assertEquals("Unknown error.", UNKNOWN_ERROR);
	}
	
	@Test
	public void testToCheckENDPoint() {
		assertEquals("https://redsky.target.com/v2/pdp/tcin/", API_URL);
		assertEquals("?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics", API_PARAM);
	}

}
