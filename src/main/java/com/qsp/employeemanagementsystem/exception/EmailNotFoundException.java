package com.qsp.employeemanagementsystem.exception;

public class EmailNotFoundException extends RuntimeException{
	
	private String message;

	public EmailNotFoundException(String message) {
		super();
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return message;
	}

}
