package com.example.entrevueSpringBoot.exception;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {   
   @ExceptionHandler(value = { ApiRequestException.class })
   public ResponseEntity<Object> handleApiRequestException(ApiRequestException e) {
	   ApiException apiException = new ApiException(e.getMessage(), HttpStatus.BAD_REQUEST, ZonedDateTime.now(ZoneId.of("Z")));
	   return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
   }
   
   @ExceptionHandler(value = { ApiNoSuchAlgorithmException.class })
   public ResponseEntity<Object> handleNoSuchAlgorithmExceltion(ApiNoSuchAlgorithmException e) {
	   ApiException apiException = new ApiException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, ZonedDateTime.now(ZoneId.of("Z")));
	   return new ResponseEntity<>(apiException, HttpStatus.INTERNAL_SERVER_ERROR);
   }
   
   @ExceptionHandler(value = { Exception.class })
   public ResponseEntity<Object> handleNoSuchAlgorithmExceltion(Exception e) {
	   ApiException apiException = new ApiException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, ZonedDateTime.now(ZoneId.of("Z")));
	   return new ResponseEntity<>(apiException, HttpStatus.INTERNAL_SERVER_ERROR);
   }
   
   @Override
   protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException e, HttpHeaders headers,
		      HttpStatus status, WebRequest request) {
	   ApiException apiException = new ApiException(e.getMessage(), HttpStatus.BAD_REQUEST, ZonedDateTime.now(ZoneId.of("Z")));
	   return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
   }
   
   
}
