package com.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Component
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleAllExceptions() {	
		return new ResponseEntity<Object>("Internal Server Error.", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(PersonNotFoundException.class)
	public ResponseEntity<Object> handlePersonNotFoundExceptions() {		
		return new ResponseEntity<Object>("Person is not found!", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ClientNotFoundException.class)
	public ResponseEntity<Object> handleClientNotFoundExceptions() {		
		return new ResponseEntity<Object>("Client is not found!", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<Object> handleUserAlreadyExistExceptions() {		
		return new ResponseEntity<Object>("Input is invalid", HttpStatus.BAD_REQUEST);
	}
}
