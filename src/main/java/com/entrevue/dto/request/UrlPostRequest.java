package com.entrevue.dto.request;

import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;

public class UrlPostRequest {
	
   @NotBlank(message = "urlToShort cannot be null or empty")
   @Schema(required = true, description = "Url to be shortened")
   private String urlToShort;

   public UrlPostRequest() {}
   
   public UrlPostRequest(String urlToShort) {
	   this.urlToShort = urlToShort;
   }
   
   public String getUrlToShort() {
		return urlToShort;
   }
	
   public void setUrlToShort(String urlToShort) {
		this.urlToShort = urlToShort;
   }
   
}
