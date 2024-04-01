package com.qsp.employeemanagementsystem.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.qsp.employeemanagementsystem.util.ResponseStructure;

@RestControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(IdNotFoundExceprtion.class)
	public ResponseEntity<ResponseStructure<String>> handleIdNotFoundException(IdNotFoundExceprtion ex) {

		ResponseStructure<String> structure = new ResponseStructure<String>();
		System.out.println("ApplicationExceptionHandler.handleIdNotFoundException()");
		structure.setMassage("Employee with given id not found");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());

		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(PhoneNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handlePhoneNotFoundException(PhoneNotFoundException ex) {

		ResponseStructure<String> structure = new ResponseStructure<String>();
		System.out.println("ApplicationExceptionHandler.handleIdNotFoundException()");
		structure.setMassage("Employee with given phone not found");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());

		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(EmailNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleIdNotFoundException(EmailNotFoundException ex) {

		ResponseStructure<String> structure = new ResponseStructure<String>();
		System.out.println("ApplicationExceptionHandler.handleIdNotFoundException()");
		structure.setMassage("Employee with given email not found");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());

		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(NoDataAvailableException.class)
	public ResponseEntity<ResponseStructure<String>> handleIdNotFoundException(NoDataAvailableException ex) {

		ResponseStructure<String> structure = new ResponseStructure<String>();
		System.out.println("ApplicationExceptionHandler.handleIdNotFoundException()");
		structure.setMassage("Employee with given data  not found");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());

		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);

	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		List<ObjectError> objectErrors = ex.getAllErrors();
		Map<String, String> map = new HashMap<String, String>();
		for (ObjectError objectError : objectErrors) {
			FieldError error = (FieldError) objectError;
			String fieldName = error.getField();
			String message = error.getDefaultMessage();

			map.put(fieldName, message);

		}
		return new ResponseEntity<Object>(map, HttpStatus.BAD_REQUEST);
	}

}
