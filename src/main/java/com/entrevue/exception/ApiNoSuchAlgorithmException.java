package com.entrevue.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ApiNoSuchAlgorithmException extends Exception {
	private static final long serialVersionUID = -5763102651400817916L;

	public ApiNoSuchAlgorithmException(String message) {
		super(message);
	}
	
	public ApiNoSuchAlgorithmException(String message, Throwable cause) {
		super(message, cause);
	}
}
