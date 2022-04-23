package com.entrevue.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.never;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.lang.reflect.Method;
import java.util.Optional;

import com.entrevue.model.Url;
import com.entrevue.util.TestMethod;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.entrevue.dto.request.UrlPostRequest;
import com.entrevue.exception.ApiNoSuchAlgorithmException;
import com.entrevue.exception.UrlShortenedNotFoundException;
import com.entrevue.mapper.UrlMapper;
import com.entrevue.repository.UrlRepository;
import com.entrevue.dto.response.UrlGetResponse;
import com.entrevue.dto.response.UrlPostResponse;

@ExtendWith(MockitoExtension.class)
public class UrlServiceTest {

	@Mock 
	private UrlRepository urlRepository;

	private AutoCloseable closeable;

	@Mock
	private UrlMapper urlMapper;
	
	private UrlService underTest;

	private final String urlToShort = "www.linkedin.com";
	private final String urlShortened = "11XEGvA98x";
	@BeforeEach
	void setUp() {
		closeable = MockitoAnnotations.openMocks(this);
		underTest = new UrlService(urlRepository, urlMapper);
	}

	@AfterEach
	void tearDown() throws Exception {
		closeable.close();
	}

	@Test
	void itShouldThrowUrlShortenedNotFound() {
		// given

		// when

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
		when(urlRepository.findByUrlShortened(urlShortened)).thenReturn(Optional.of(urlOptional));
		when(urlMapper.mapToUrlGetResponse(urlOptional)).thenReturn(urlGetResponse);

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
		when(urlMapper.mapToUrlPostResponse(urlToSave)).thenReturn(urlPostResponse);
		when(urlMapper.mapToUrl(urlPostRequest, urlShortened)).thenReturn(urlToSave);
		when(urlRepository.save(urlToSave)).thenReturn(urlToSave);

		underTest.saveUrl(urlPostRequest);

		// then
		verify(urlRepository).save(urlToSave);

	}
	
	@Test
	void itShouldSaveUrl() {
		// given
		Url urlToSave = new Url(urlShortened, urlToShort);

		// when
		underTest.saveUrl(urlToSave);
		ArgumentCaptor<Url> urlArgumentCaptor = ArgumentCaptor.forClass(Url.class);
		verify(urlRepository).save(urlArgumentCaptor.capture());
		Url capturedUrl = urlArgumentCaptor.getValue();

		// then
		assertThat(capturedUrl).isEqualTo(urlToSave);
	}

	@Test
	void itShouldNotThrowApiNoSuchAlgorithmException() throws NoSuchMethodException {
		// given
		UrlService urlService = new UrlService(urlRepository, urlMapper);

		// when
		Method privateMethod = UrlService.class.getDeclaredMethod("getMessageDigest", String.class );
		privateMethod.setAccessible(true);

		// then
		assertDoesNotThrow(() -> {
			privateMethod.invoke(urlService, "MD5");
		});
	}

	@Test
	void itShouldThrowApiNoSuchAlgorithmException() throws ReflectiveOperationException {
		// given
		UrlService urlService = new UrlService(urlRepository, urlMapper);
		String algorithm = "AnyAlgorithmThatDoesntExist";

		// when
		TestMethod<UrlService, String> method =
				TestMethod.method(UrlService.class, "getMessageDigest", String.class);

		// then
		assertThrows(ApiNoSuchAlgorithmException.class, () -> method.invoke(urlService, algorithm));
	}
}
