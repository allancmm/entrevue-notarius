package com.entrevue.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
      
   @ExceptionHandler(UrlShortenedNotFoundException.class)
   public <T> ResponseEntity<ApiException> handleUrlShortenedNotFoundException(UrlShortenedNotFoundException e) {	   
	   ApiException apiException = ApiException.builder()
			                           .message(e.getMessage())
			                           .httpStatus(HttpStatus.NOT_FOUND)
			                           .build();
	   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiException);
   }      
   
   @ExceptionHandler({ Exception.class })
   public ResponseEntity<ApiException> handleGenericException(Exception e) {
	   List<String> details = new ArrayList<>();
	   details.add(e.getLocalizedMessage());
	   ApiException apiException = ApiException.builder()
               .message(e.getMessage())
               .details(details)
               .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
               .build();
	   return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiException);
   }

   @ExceptionHandler(NullPointerException.class)
   public ResponseEntity<ApiException> handleException(NullPointerException e) {
	   ApiException apiException = ApiException.builder()
               .message("We tried to read a null object, what a shame")
               .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
               .build();
	   return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiException);
   }
   
   
   // 
   
   @Override
   protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException e, HttpHeaders headers,
		      HttpStatus status, WebRequest request) {
	   List<String> details = new ArrayList<>();
	   details.add(e.getLocalizedMessage());
	   ApiException apiException = ApiException.builder()
               .message(e.getMessage())
               .details(details)
               .httpStatus(HttpStatus.BAD_REQUEST)
               .build();
	   return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiException);
   }
   
   @Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {	
	   
	   ex.getBindingResult()
	   .getFieldErrors().stream().forEach( f -> System.out.println(f.getDefaultMessage()));
	   
	   ApiException apiException = ApiException.builder()
               .message(ex.getMessage())
               .httpStatus(HttpStatus.BAD_REQUEST)
               .build();
	   
	   return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiException);
	}
   
   @Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
	   ApiException apiException = ApiException.builder()
               .message(ex.getMessage())
               .httpStatus(HttpStatus.METHOD_NOT_ALLOWED)
               .build();
     	return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(apiException);  
	}   
   
   @Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
	   ApiException apiException = ApiException.builder()
               .message(ex.getMessage())
               .httpStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
               .build();
       return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(apiException);   
	}
}
