package com.entrevue.dto.request;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class UrlPostRequestTest {
	static Validator validator;
	
	@BeforeAll 
	public static void setupValidatorInstance() {
		validator = Validation.buildDefaultValidatorFactory().getValidator();
	}
	
	@Test
	public void whenNotEmptyUrl_thenNoConstraintViolations() {
		// given
		UrlPostRequest urlPostRequest = new UrlPostRequest("www.google.com");
		
		// when
		Set<ConstraintViolation<UrlPostRequest>> violations = validator.validate(urlPostRequest);
		
		// then
		assertThat(violations.size()).isEqualTo(0);
	}
	
	@Test
	public void whenEmptyUrl_thenOneConstraintViolation() {
		// give
		UrlPostRequest urlPostRequest = new UrlPostRequest("");
		
		// when
		Set<ConstraintViolation<UrlPostRequest>> violations = validator.validate(urlPostRequest);
		
		// then
        assertThat(violations.size()).isEqualTo(1);
		
	}
	
	@Test
	public void whenNullUrl_thenOneConstraintViolation() {
		// give
		UrlPostRequest urlPostRequest = new UrlPostRequest();
		
		// when
		Set<ConstraintViolation<UrlPostRequest>> violations = validator.validate(urlPostRequest);
		
		// then
        assertThat(violations.size()).isEqualTo(1);
		
	}

}
