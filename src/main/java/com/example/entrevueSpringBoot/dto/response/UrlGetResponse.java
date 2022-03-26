package com.example.entrevueSpringBoot.dto.response;

/*
 * @author Allan Martins
 */

public class UrlGetResponse {
    private String urlOriginal;
    private String urlShortned;
   
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
