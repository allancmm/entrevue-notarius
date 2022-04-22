package com.entrevue.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import com.entrevue.dto.response.UrlGetResponse;
import com.entrevue.dto.response.UrlPostResponse;
import com.entrevue.model.Url;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.entrevue.dto.request.UrlPostRequest;
import com.entrevue.exception.ApiNoSuchAlgorithmException;
import com.entrevue.exception.UrlShortenedNotFoundException;
import com.entrevue.mapper.UrlMapper;
import com.entrevue.repository.UrlRepository;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
public class UrlServiceTest {

	@Mock 
	private UrlRepository urlRepository;
	
	@Mock
	private UrlMapper urlMapper;
	
	private UrlService underTest;

	private final String urlToShort = "www.linkedin.com";
	private final String urlShortened = "11XEGvA98x";
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		underTest = new UrlService(urlRepository, urlMapper);
	}

	@Test
	void itShouldThrowUrlShortenedNotFound() {
		// given

		// then
		assertThatThrownBy(() -> underTest.getByUrlShortened(urlShortened))
				.isInstanceOf(UrlShortenedNotFoundException.class)
				.hasMessageContaining("Url not found: " + urlShortened);
		verify(urlMapper, never()).mapToUrlGetResponse(any());
	}
	
	@Test
	void itShouldGeUrlShortened() {
		// given
		Url urlOptional = new Url(urlShortened, urlToShort);
		UrlGetResponse urlGetResponse = new UrlGetResponse(urlToShort, urlShortened);

		// when
		Mockito.when(urlRepository.findByUrlShortened(urlShortened)).thenReturn(Optional.of(urlOptional));
		Mockito.when(urlMapper.mapToUrlGetResponse(urlOptional)).thenReturn(urlGetResponse);

		// then
		assertThat(underTest.getByUrlShortened(urlShortened)).isEqualTo(urlGetResponse);
	}
	
	@Test
	void canSaveUrl() throws ApiNoSuchAlgorithmException {		
		// given
		UrlPostRequest urlPostRequest = new UrlPostRequest(urlToShort);
		Url urlToSave = new Url(urlShortened, urlToShort);
		UrlPostResponse urlPostResponse = new UrlPostResponse();

		// when 
		underTest.saveUrl(urlPostRequest);
		Mockito.when(urlMapper.mapToUrlPostResponse(urlToSave)).thenReturn(urlPostResponse);

		// then
		verify(urlRepository).save(urlToSave);

//		Url capturedUrl = urlArgumentCaptor.getValue();
//		assertThat(capturedUrl).isEqualTo(urlToSave);
	}
	
	@Test
	void itShouldSaveUrl() {
		// given
		Url urlToSave = new Url(urlShortened, urlToShort);

		// when
		underTest.saveUrl(urlToSave);
		
		// then
		ArgumentCaptor<Url> urlArgumentCaptor = ArgumentCaptor.forClass(Url.class);
		verify(urlRepository).save(urlArgumentCaptor.capture());

		Url capturedUrl = urlArgumentCaptor.getValue();

		assertThat(capturedUrl).isEqualTo(urlToSave);
	}

//	@Test
//	void itShouldThrowApiNoSuchAlgorithmException() throws NoSuchAlgorithmException {
//		// given
//		Url urlToSave = new Url(urlShortened, urlToShort);
//
//		// when
//		underTest.saveUrl(urlToSave);
//		//
//
//	}
}
