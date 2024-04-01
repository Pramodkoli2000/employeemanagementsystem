package com.qsp.employeemanagementsystem.exception;

public class NoDataAvailableException extends RuntimeException {

	private String message;

	public NoDataAvailableException(String message) {

		this.message = message;
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return message;
	}
}
