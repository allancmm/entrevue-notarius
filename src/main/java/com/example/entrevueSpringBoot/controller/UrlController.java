package com.example.entrevueSpringBoot.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entrevueSpringBoot.dto.request.UrlPostRequest;
import com.example.entrevueSpringBoot.dto.response.UrlGetResponse;
import com.example.entrevueSpringBoot.dto.response.UrlPostResponse;
import com.example.entrevueSpringBoot.service.UrlService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.security.NoSuchAlgorithmException;
/*
 * @author Allan Martins
 */
@RestController
@RequestMapping(path = "/api/v1/url", produces = APPLICATION_JSON_VALUE)
public class UrlController {
			
	@Autowired
	private UrlService urlService;
			
	@Operation(summary = "Retourne URL basé sur ID")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "URL trouvé", 
	                                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, 
	                                    schema = @Schema(implementation = UrlGetResponse.class))}),
	                       @ApiResponse(responseCode = "404", description = "URL non-trouvé", content = @Content)}) 
	@GetMapping("/{urlShortned}")
	public ResponseEntity<UrlGetResponse> getURL(@PathVariable("urlShortned") String urlShortned){
		final Optional<UrlGetResponse> url = urlService.getURLShortned(urlShortned);
		if(url.isEmpty()) {
			return ResponseEntity.notFound().build();
	    }		
         return new ResponseEntity<UrlGetResponse>(url.get(), HttpStatus.OK);
	}
	
	@Operation(summary = "Short URL")
	@ApiResponse(responseCode = "201", description = "Shorter URL crée", 
	             content = {@Content(mediaType = APPLICATION_JSON_VALUE,
	             schema = @Schema(implementation = UrlPostResponse.class))})
	@PostMapping(consumes = APPLICATION_JSON_VALUE) 
	public ResponseEntity<UrlPostResponse> postURL(@Valid @RequestBody UrlPostRequest request) throws NoSuchAlgorithmException {    
		return new ResponseEntity<UrlPostResponse>(urlService.saveUrl(request), HttpStatus.CREATED);
	}

}
