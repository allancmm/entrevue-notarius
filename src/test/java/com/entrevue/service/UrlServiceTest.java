package com.entrevue.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.entrevue.dto.request.UrlPostRequest;
import com.entrevue.exception.ApiNoSuchAlgorithmException;
import com.entrevue.exception.UrlShortenedNotFoundException;
import com.entrevue.mapper.UrlMapper;
import com.entrevue.model.Url;
import com.entrevue.repository.UrlRepository;

@ExtendWith(MockitoExtension.class)
public class UrlServiceTest {

	@Mock 
	private UrlRepository urlRepository;
	
	@Mock
	private UrlMapper urlMapper;
	
	private UrlService underTest;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		underTest = new UrlService(urlRepository, urlMapper);
	}
	
	
	@Test
	void cantGetByUrlShortened() {
		// given
		String urlShortened = "DtzJjunD0N";
		
		// then
		assertThrows(UrlShortenedNotFoundException.class, () -> underTest.getByUrlShortened(urlShortened));
	}
	
	@Test
	void canGetByUrlShortened() {
		// given
		String urlShortened = "";
		
		// when
		underTest.getByUrlShortened(urlShortened);
		
		// then
		verify(urlRepository).findByUrlShortened(urlShortened);
	}
	
	@Test
	void canSaveUrl() throws ApiNoSuchAlgorithmException {		
		// given
		String urlToShort = "www.linkedin.com";
		String urlShortened = "11XEGvA98x";
		UrlPostRequest urlPostRequest = new UrlPostRequest(urlToShort);
		//Url urlToSave = new Url(urlShortened, urlToShort);
				// urlMapper.mapToUrl(urlPostRequest, urlShortened);
		
		// when 
		underTest.saveUrl(urlPostRequest);
		
		// then
		verify(urlMapper).mapToUrl(urlPostRequest, urlShortened);
		// verify(urlRepository).save(urlToSave);
	}
}
