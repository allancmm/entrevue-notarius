package com.example.entrevueSpringBoot.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
      
   @ExceptionHandler(UrlShortenedNotFoundException.class)
   public <T> ResponseEntity<ApiException> handleUrlShortenedNotFoundException(UrlShortenedNotFoundException e) {
	   ApiException apiException = new ApiException(e.getMessage(), HttpStatus.NOT_FOUND);
	   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiException);
   }      
   
   @ExceptionHandler({ Exception.class, ApiNoSuchAlgorithmException.class})
   public ResponseEntity<ApiException> handleGenericException(Exception e) {
	   List<String> details = new ArrayList<>();
	   details.add(e.getLocalizedMessage());
	   ApiException apiException = new ApiException(e.getMessage(), details, HttpStatus.INTERNAL_SERVER_ERROR);
	   return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiException);
   }

   @ExceptionHandler(NullPointerException.class)
   public ResponseEntity<ApiException> handleException(NullPointerException e) {
	   ApiException apiException = new ApiException("We tried to read a null object, what a shame", HttpStatus.INTERNAL_SERVER_ERROR);
	   return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiException);
   }
   
   @Override
   protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException e, HttpHeaders headers,
		      HttpStatus status, WebRequest request) {
	   List<String> details = new ArrayList<>();
	   details.add(e.getLocalizedMessage());
	   ApiException apiException = new ApiException(e.getMessage(), details, HttpStatus.BAD_REQUEST);
	   return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiException);
   }
   
   @Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
     	ApiException apiException = new ApiException(ex.getMessage(), HttpStatus.METHOD_NOT_ALLOWED);
     	return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(apiException);  
	}   
   
   @Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
       ApiException apiException = new ApiException(ex.getMessage(), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
       return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(apiException);   
	}
}
