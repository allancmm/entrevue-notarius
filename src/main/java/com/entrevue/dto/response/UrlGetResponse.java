package com.entrevue.dto.response;

/*
 * @author Allan Martins
 */

public class UrlGetResponse {
    private String urlOriginal;
    private String urlShortened;

	public UrlGetResponse() {}

	public UrlGetResponse(String urlOriginal, String urlShortened) {
		this.urlOriginal = urlOriginal;
		this.urlShortened = urlShortened;
	}

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
