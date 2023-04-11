package com.example.demo.exception;

public class MyException extends RuntimeException {
	public MyException(String message) {
	super(message);
	}	
	
	public MyException() {
		System.out.println("Resource not found");
	}
	

}
