package com.javaweb.advice;

import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(value = {SQLException.class})
	public ResponseEntity<Object> handleSyntaxException(SQLException ex) {
	    String message = ex.getMessage();
	    return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
	}


}
