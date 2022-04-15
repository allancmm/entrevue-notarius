package com.example.entrevueSpringBoot.exception;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

public class ApiException {
   private final String message;
   private final List<String> details;
   private final HttpStatus httpStatus;
   private final ZonedDateTime timestamp;
   
   public ApiException(String message, List<String> details, HttpStatus httpStatus, ZonedDateTime timestamp) {
	   this.message = message;
	   this.details = details;
	   this.httpStatus = httpStatus;
	   this.timestamp = timestamp;	
   }
   
   public ApiException(String message, HttpStatus httpStatus, ZonedDateTime timestamp) {
	   this.message = message;
	   this.details = null;
	   this.httpStatus = httpStatus;
	   this.timestamp = timestamp;	
   }
   
   public ApiException(String message, List<String> details, HttpStatus httpStatus) {
	   this.message = message;
	   this.details = details;
	   this.httpStatus = httpStatus;
	   this.timestamp = ZonedDateTime.now(ZoneId.of("UTC"));	
   }
   
   public ApiException(String message, HttpStatus httpStatus) {
	   this.message = message;
	   this.details = null;
	   this.httpStatus = httpStatus;
	   this.timestamp = ZonedDateTime.now(ZoneId.of("UTC"));	
   }

	public String getMessage() {
		return message;
	}
	
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	
	public List<String> getDetails() {
		return details;
	}

	public ZonedDateTime getTimestamp() {
		return timestamp;
	}
}