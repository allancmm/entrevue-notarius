package com.example.entrevueSpringBoot.exception;

public class ApiRequestException extends RuntimeException {

	private static final long serialVersionUID = -739289743435281056L;

	public ApiRequestException(String message) {
		super(message);
	}
	
	public ApiRequestException(String message, Throwable cause) {
		super(message, cause);
	}
}
