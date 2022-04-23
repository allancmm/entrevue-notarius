package com.entrevue.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class UrlTest {
	
	@Test
	public void itShouldNotThrowException() {
		// given
		String urlShortened = "CY9rzUYh03";
		String urlOriginal = "www.google.com";			

		// when

		// then		
		assertDoesNotThrow(() -> {
			new Url(urlShortened, urlOriginal);
		});
	}
	
	@Test
	public void itShouldThrowNullPointerException() {
		// given
		// when
		// then		
		assertThrows(NullPointerException.class, () -> new Url(null, null));
	}
	
}
