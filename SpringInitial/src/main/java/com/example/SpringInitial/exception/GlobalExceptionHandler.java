package com.example.SpringInitial.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(ArithmeticException.class)
	public ResponseEntity<?> handleRuntimeException(HttpServletRequest request, ArithmeticException t){
		LOGGER.error(t.getMessage(), t);
		
		Map<String, String> map = new HashMap<>();
		map.put("error", "An arithmetic error ocuured");
		
		return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(Throwable.class)
	public ResponseEntity<?> handleRuntimeException(HttpServletRequest request, Throwable t){
		LOGGER.error(t.getMessage(), t);
		
		Map<String, String> map = new HashMap<>();
		map.put("error", "An unknown error occured");
		
		return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
