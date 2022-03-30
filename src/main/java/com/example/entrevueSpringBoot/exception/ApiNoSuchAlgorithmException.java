package com.example.entrevueSpringBoot.exception;

public class ApiNoSuchAlgorithmException extends Exception {
	private static final long serialVersionUID = -5763102651400817916L;

	public ApiNoSuchAlgorithmException(String message) {
		super(message);
	}
	
	public ApiNoSuchAlgorithmException(String message, Throwable cause) {
		super(message, cause);
	}
}
