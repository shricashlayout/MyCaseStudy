package com.myretail.errorhandler;

public class MyRetailErrorInfo {
	private String message;
	
	public MyRetailErrorInfo() {		
	}
	public MyRetailErrorInfo(String message) {
		super();
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
