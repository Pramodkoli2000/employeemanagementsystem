package com.qsp.employeemanagementsystem.util;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ResponseStructure<T> {
	
	private String massage;
	private int status;
	private T data;
	

}
