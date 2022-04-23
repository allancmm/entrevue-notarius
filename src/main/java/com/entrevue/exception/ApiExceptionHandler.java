package com.entrevue.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/*
 * @author Allan Martins
 */
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

   private static final Logger LOG = LoggerFactory.getLogger(ApiExceptionHandler.class);

	
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
	   LOG.error("Exception", e);
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
	   LOG.error("NullPointerException", e);
	   ApiException apiException = ApiException.builder()
               .message("We tried to read a null object, what a shame")
               .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
               .build();
	   return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiException);
   }
   
   @ExceptionHandler(ConstraintViolationException.class)
   public ResponseEntity<ApiException> handleConstraintViolationException(ConstraintViolationException e) {
	   LOG.error("Exception", e);
	   List<String> details = new ArrayList<>();
	   // TODO - separate each error message
	   details.add(e.getLocalizedMessage());
	   ApiException apiException = ApiException.builder()
               .message("Error validation")
               .details(details)
               .httpStatus(HttpStatus.BAD_REQUEST)
               .build();
	   return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiException);
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
	   List<String> details = ex.getBindingResult()
	                                .getFieldErrors()
	                                .stream()
	                                .map(FieldError::getDefaultMessage)
	                                .collect(Collectors.toList());
	   
	   ApiException apiException = ApiException.builder()
               .message("Error validation")
               .details(details)
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
