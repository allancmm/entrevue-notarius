package com.entrevue.exception;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

public class ApiException {
   private final String message;
   private final List<String> details;
   private final Integer status;
   private final String error;
   private final ZonedDateTime timestamp;
   
   public ApiException(Builder builder) {
	   this.message = builder.message;
	   this.details = builder.details;
	   this.status = builder.httpStatus.value();
	   this.error = builder.httpStatus.name();	  
	   this.timestamp = ZonedDateTime.now(ZoneId.of("UTC"));	  
   }
   
   public static Builder builder() {
	   return new Builder();
   }
   
   public static class Builder {
	   public String message;
	   public List<String> details;
	   public HttpStatus httpStatus;
	   public ZonedDateTime timestamp;
	   
	   public ApiException build() {
		   return new ApiException(this);
	   }
	   
	   public Builder message(String message) {
		   this.message = message;
		   return this;
	   }
	   
	   public Builder details(List<String> details) {
		   this.details = details;
		   return this;
	   }
	   
	   public Builder httpStatus(HttpStatus httpStatus) {
		   this.httpStatus = httpStatus;
		   return this;
	   }
   }
   
	public String getMessage() {
		return message;
	}
	
	public Integer getStatus() {
		return status;
	}

	public String getError() {
		return error;
	}

	public List<String> getDetails() {
		return details;
	}

	public ZonedDateTime getTimestamp() {
		return timestamp;
	}
}
