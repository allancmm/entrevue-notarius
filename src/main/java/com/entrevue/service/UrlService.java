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
import com.entrevue.repository.UrlRepository;

/*
 * @author Allan Martins
 */

@Service
public class UrlService {

	private static final Logger LOG = LoggerFactory.getLogger(UrlService.class);

	private final UrlRepository urlRepository;
	private final UrlMapper urlMapper;
	public UrlService(UrlRepository urlRepository, UrlMapper urlMapper) {
		this.urlRepository = urlRepository;
		this.urlMapper = urlMapper;
	}

	public UrlGetResponse getByUrlShortened(String urlShortened) {
		final Url url = urlRepository.findByUrlShortened(urlShortened)
				                     .orElseThrow(() -> {
				             			 LOG.warn("Url not found: " + urlShortened);
				                    	 return new UrlShortenedNotFoundException("Url not found: " + urlShortened);
				                     });
		return urlMapper.mapToUrlGetResponse(url);
   }

   public UrlPostResponse saveUrl(UrlPostRequest urlRequest) throws ApiNoSuchAlgorithmException {
	    final String HASH_ALGORITHM = "MD5";
	    final int MAX_LENGTH_URL_SHORTENED = 10;

	    MessageDigest md = getMessageDigest(HASH_ALGORITHM);
	    md.update(urlRequest.getUrlToShort().getBytes());
	    byte[] encodedUrlShortened = Base64.getUrlEncoder().encode(md.digest());
	    String urlShortened = new String(encodedUrlShortened).substring(0, MAX_LENGTH_URL_SHORTENED);
   	    return urlMapper.mapToUrlPostResponse(urlRepository.save(urlMapper.mapToUrl(urlRequest, urlShortened)));
   }

   public Url saveUrl(Url urlToSave) {
	   return urlRepository.save(urlToSave);
   }

   private MessageDigest getMessageDigest(String algorithm) throws ApiNoSuchAlgorithmException {
	   try {
		   return MessageDigest.getInstance(algorithm);
	   } catch (NoSuchAlgorithmException e) {
		   LOG.error("Error occurred in saveUrl", e);
		   throw new ApiNoSuchAlgorithmException("Oops, it seems that we have a problem");
	   }
   }
}
