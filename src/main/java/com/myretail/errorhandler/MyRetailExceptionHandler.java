package com.myretail.errorhandler;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.myretail.exception.ProductNotFoundException;

import static com.myretail.constants.MyRetailAppConstants.PRODUCT_NOT_FOUND;
import static com.myretail.constants.MyRetailAppConstants.UNKNOWN_ERROR;

@RestControllerAdvice
public class MyRetailExceptionHandler  extends ResponseEntityExceptionHandler {
	
	private final Logger LOG = LoggerFactory.getLogger(getClass());
    
	@ExceptionHandler({Exception.class})
	protected ResponseEntity<MyRetailErrorInfo> handleUnknownError(HttpServletRequest request, Exception exception) {
		LOG.error(exception.getMessage());
		return new ResponseEntity<MyRetailErrorInfo>(new MyRetailErrorInfo(UNKNOWN_ERROR), HttpStatus.UPGRADE_REQUIRED);
	}
    
    @ExceptionHandler({ProductNotFoundException.class})
	public ResponseEntity<MyRetailErrorInfo> handleProductNotFoundCase(HttpServletRequest request, Exception exception) {
    	LOG.error(exception.getMessage());
    	return new ResponseEntity<MyRetailErrorInfo>(new MyRetailErrorInfo(PRODUCT_NOT_FOUND), HttpStatus.NOT_FOUND);
	}
}
