package com.myretail.errorhandler;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

import com.myretail.exception.ProductNotFoundException;

public class ProductExceptionHandler extends DefaultResponseErrorHandler {

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		if(response.getStatusCode() == HttpStatus.NOT_FOUND) throw new ProductNotFoundException("Product not found");
		else throw new RuntimeException();
	}
}
