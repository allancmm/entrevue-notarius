package com.example.entrevueSpringBoot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UrlShortenedNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UrlShortenedNotFoundException(String message) {
    	super(message);
    }
}
