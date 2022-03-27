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
	
	private final String HASH_ALGORITHM = "MD5";
	private final int MAX_LENGHT_URL_SHORTNED = 10;

	public Optional<UrlGetResponse> geturlShortened(String urlShortened) {
		final Optional<Url> url = urlRepository.findByurlShortened(urlShortened);
		if(url.isEmpty()) {
			return Optional.empty();
		}
		
		return Optional.of(urlMapper.mapToUrlGetResponse(url.get()));
   }
	
   public UrlPostResponse saveUrl(UrlPostRequest urlRequest) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance(HASH_ALGORITHM);
	    md.update(urlRequest.urlToShort.getBytes());	   	    
	    byte[] encodedurlShortened = Base64.getUrlEncoder().encode(md.digest());
	    String urlShortened = new String(encodedurlShortened).substring(0, MAX_LENGHT_URL_SHORTNED);	    
   	    return urlMapper.mapToUrlPostResponse(urlRepository.save(urlMapper.mapToUrl(urlRequest, urlShortened)));	    
   }
}
