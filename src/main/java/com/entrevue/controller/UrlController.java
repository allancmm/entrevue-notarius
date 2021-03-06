package com.entrevue.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entrevue.dto.request.UrlPostRequest;
import com.entrevue.dto.response.UrlGetResponse;
import com.entrevue.dto.response.UrlPostResponse;
import com.entrevue.exception.ApiNoSuchAlgorithmException;
import com.entrevue.service.UrlService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/*
 * @author Allan Martins
 */

@RestController
@RequestMapping(path = "/api/v1/url", produces = APPLICATION_JSON_VALUE)
@Tag(name = "URL")
public class UrlController {

	private final UrlService urlService;

	public UrlController(UrlService urlService) {
		this.urlService = urlService;
	}

	@Operation(summary = "Return URL based on ID")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "URL found", 
	                                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, 
	                                    schema = @Schema(implementation = UrlGetResponse.class))}),
	                       @ApiResponse(responseCode = "404", description = "URL not fount", content = @Content)}) 
	@GetMapping
	public ResponseEntity<UrlGetResponse> getUrl(@RequestParam(value = "urlShortened") String urlShortened) {
         return new ResponseEntity<>(urlService.getByUrlShortened(urlShortened), HttpStatus.OK);
	}
	
	@Operation(summary = "Short URL")
	@ApiResponse(responseCode = "201", description = "Shorter URL created", 
	             content = {@Content(mediaType = APPLICATION_JSON_VALUE,
	             schema = @Schema(implementation = UrlPostResponse.class))})
	@PostMapping(consumes = APPLICATION_JSON_VALUE) 
	public ResponseEntity<UrlPostResponse> postUrl(@Valid @RequestBody UrlPostRequest urlPostRequest) throws ApiNoSuchAlgorithmException  {	
		return new ResponseEntity<>(urlService.saveUrl(urlPostRequest), HttpStatus.CREATED);
	}
}
