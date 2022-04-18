package com.entrevue.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.entrevue.dto.request.UrlPostRequest;
import com.entrevue.dto.response.UrlGetResponse;
import com.entrevue.dto.response.UrlPostResponse;
import com.entrevue.exception.ApiNoSuchAlgorithmException;
import com.entrevue.exception.UrlShortenedNotFoundException;
import com.entrevue.mapper.UrlMapper;
import com.entrevue.model.Url;
import com.entrevue.repository.URLRepository;

/*
 * @author Allan Martins
 */

@Service
public class UrlService {

	private static final Logger LOG = LoggerFactory.getLogger(UrlService.class);

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
				                     .orElseThrow(() -> {
				             			 LOG.warn("Url not found: " + urlShortened);
				                    	 return new UrlShortenedNotFoundException("Url not found: " + urlShortened);
				                     });
		return urlMapper.mapToUrlGetResponse(url);
   }
	
   public UrlPostResponse saveUrl(UrlPostRequest urlRequest) throws ApiNoSuchAlgorithmException {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance(HASH_ALGORITHM);
		} catch (NoSuchAlgorithmException e) {
			LOG.error("Error occurred in saveUrl", e);
			throw new ApiNoSuchAlgorithmException("Oops, it seems that we have a problem");
		}

	    md.update(urlRequest.urlToShort.getBytes());	   	    
	    byte[] encodedurlShortened = Base64.getUrlEncoder().encode(md.digest());
	    String urlShortened = new String(encodedurlShortened).substring(0, MAX_LENGHT_URL_SHORTNED);	    
   	    return urlMapper.mapToUrlPostResponse(urlRepository.save(urlMapper.mapToUrl(urlRequest, urlShortened)));	    
   }
}
