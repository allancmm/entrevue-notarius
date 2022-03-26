package com.example.entrevueSpringBoot.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entrevueSpringBoot.dto.request.UrlPostRequest;
import com.example.entrevueSpringBoot.dto.response.UrlGetResponse;
import com.example.entrevueSpringBoot.dto.response.UrlPostResponse;
import com.example.entrevueSpringBoot.mapper.UrlMapper;
import com.example.entrevueSpringBoot.model.Url;
import com.example.entrevueSpringBoot.repository.URLRepository;

/*
 * @author Allan Martins
 */

@Service
public class UrlService {

	@Autowired
	private URLRepository urlRepository;
	
	@Autowired
	private UrlMapper urlMapper;
	
	private final String MD5 = "MD5";
	private final int MAX_LENGHT_URL_SHORTNED = 10;

	public Optional<UrlGetResponse> getURLShortned(String urlShortned) {
		final Optional<Url> url = urlRepository.findByUrlShortned(urlShortned);
		if(url.isEmpty()) {
			return Optional.empty();
		}
		
		return Optional.of(urlMapper.mapToUrlGetResponse(url.get()));
   }
	
   public UrlPostResponse saveUrl(UrlPostRequest urlRequest) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance(MD5);
	    md.update(urlRequest.urlToShort.getBytes());	   	    
	    byte[] encodedUrlShortned = Base64.getEncoder().encode(md.digest());
	    String urlShortned = new String(encodedUrlShortned).substring(0, MAX_LENGHT_URL_SHORTNED);	    
   	    return urlMapper.mapToUrlPostResponse(urlRepository.save(urlMapper.mapToUrl(urlRequest, urlShortned)));	    
   }
}
