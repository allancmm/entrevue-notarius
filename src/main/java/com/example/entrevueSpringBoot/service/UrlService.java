package com.example.entrevueSpringBoot.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.springframework.stereotype.Service;

import com.example.entrevueSpringBoot.dto.request.UrlPostRequest;
import com.example.entrevueSpringBoot.dto.response.UrlGetResponse;
import com.example.entrevueSpringBoot.dto.response.UrlPostResponse;
import com.example.entrevueSpringBoot.exception.ApiNoSuchAlgorithmException;
import com.example.entrevueSpringBoot.exception.UrlShortenedNotFoundException;
import com.example.entrevueSpringBoot.mapper.UrlMapper;
import com.example.entrevueSpringBoot.model.Url;
import com.example.entrevueSpringBoot.repository.URLRepository;

/*
 * @author Allan Martins
 */

@Service
public class UrlService {

	private final URLRepository urlRepository;
	
	private final UrlMapper urlMapper;
	
	public UrlService(URLRepository urlRepository, UrlMapper urlMapper) {
		this.urlRepository = urlRepository;
		this.urlMapper = urlMapper;
	}
	
	private final String HASH_ALGORITHM = "MD5";
	private final int MAX_LENGHT_URL_SHORTNED = 10;

	public UrlGetResponse getUrlShortened(String urlShortened) {
		final Url url = urlRepository.findByurlShortened(urlShortened)
				                     .orElseThrow(() -> new UrlShortenedNotFoundException("Url not found: " + urlShortened));
		return urlMapper.mapToUrlGetResponse(url);
   }
	
   public UrlPostResponse saveUrl(UrlPostRequest urlRequest) throws ApiNoSuchAlgorithmException {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance(HASH_ALGORITHM);
		} catch (NoSuchAlgorithmException e) {
			throw new ApiNoSuchAlgorithmException("Oups, it seems that we have a problem");
		}
	    md.update(urlRequest.urlToShort.getBytes());	   	    
	    byte[] encodedurlShortened = Base64.getUrlEncoder().encode(md.digest());
	    String urlShortened = new String(encodedurlShortened).substring(0, MAX_LENGHT_URL_SHORTNED);	    
   	    return urlMapper.mapToUrlPostResponse(urlRepository.save(urlMapper.mapToUrl(urlRequest, urlShortened)));	    
   }
}
