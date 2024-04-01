package com.qsp.employeemanagementsystem.exception;

public class PhoneNotFoundException extends RuntimeException {

	private String message;

	public PhoneNotFoundException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return message;
	}
}
