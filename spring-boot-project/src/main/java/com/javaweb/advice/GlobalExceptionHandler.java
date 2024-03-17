package com.javaweb.advice;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(value = {SQLException.class})
	public ResponseEntity<?> handleSyntaxException(SQLException ex) {
	    String message = ex.getMessage();
	    return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = {NumberFormatException.class})
	public ResponseEntity<?> handleNumberFormatException(NumberFormatException ex) {
	    Map<String, String> error = new LinkedHashMap<>();
	    error.put("error", "not a number");
	    error.put("detail", "expect numbers in numeric fields");
	    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}
