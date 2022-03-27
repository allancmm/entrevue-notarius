package com.example.entrevueSpringBoot.dto.response;

/*
 * @author Allan Martins
 */

public class UrlGetResponse {
    private String urlOriginal;
    private String urlShortened;
   
	public String getUrlOriginal() {
		return urlOriginal;
	}
	public void setUrlOriginal(String urlOriginal) {
		this.urlOriginal = urlOriginal;
	}
	public String geturlShortened() {
		return urlShortened;
	}
	public void seturlShortened(String urlShortened) {
		this.urlShortened = urlShortened;
	}   
}
