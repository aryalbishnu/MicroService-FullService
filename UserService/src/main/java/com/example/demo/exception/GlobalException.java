package com.example.demo.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

@ExceptionHandler(MyException.class)
public ResponseEntity<ApiResponse> handleResourceNotFound(MyException ex) {
String message = ex.getMessage();
ApiResponse response = ApiResponse.builder().message(message).success(true).status(HttpStatus.INTERNAL_SERVER_ERROR).build();
return new ResponseEntity<ApiResponse>(response, HttpStatus.BAD_REQUEST);
}
	

}
