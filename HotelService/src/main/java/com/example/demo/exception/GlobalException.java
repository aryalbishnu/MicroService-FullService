package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
	@ExceptionHandler(ResourceNotFoundException.class)
	
	public ResponseEntity<ApiResponse> handlerResourceNotFound(ResourceNotFoundException ex){
	  String message = ex.getMessage();
	  ApiResponse response = ApiResponse.builder().message(message).success(true).status(HttpStatus.BAD_REQUEST).build();
	  return new ResponseEntity<ApiResponse>(response, HttpStatus.BAD_REQUEST);
	}

}
