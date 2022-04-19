package com.entrevue.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

/*
 * @author Allan Martins
 */
@Document(collection = "Url")
public class Url {

	@Id
	private String urlShortened;
	
	private String urlOriginal;
	
	public Url(String urlShortened, String urlOriginal) {
		this.urlShortened = urlShortened;
		this.urlOriginal = urlOriginal;
	}
	
	public Url() {}
	
	public String getUrlShortened() {
		return urlShortened;
	}

	public void setUrlShortened(String urlShortened) {
		this.urlShortened = urlShortened;
	}

	public String getUrlOriginal() {
		return urlOriginal;
	}

	public void setUrlOriginal(String urlOriginal) {
		this.urlOriginal = urlOriginal;
	}
}
