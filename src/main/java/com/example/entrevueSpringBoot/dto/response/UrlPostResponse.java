package com.example.entrevueSpringBoot.dto.response;

public class UrlPostResponse {
   private Long id;
   private String urlOriginal;
   private String urlShortned;
   
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUrlOriginal() {
		return urlOriginal;
	}
	public void setUrlOriginal(String urlOriginal) {
		this.urlOriginal = urlOriginal;
	}
	public String getUrlShortned() {
		return urlShortned;
	}
	public void setUrlShortned(String urlShortned) {
		this.urlShortned = urlShortned;
	}   
}
