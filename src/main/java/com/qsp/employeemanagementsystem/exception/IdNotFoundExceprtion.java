package com.qsp.employeemanagementsystem.exception;

public class IdNotFoundExceprtion extends RuntimeException {

	private String message;

	public IdNotFoundExceprtion(String message) {
		super();
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
