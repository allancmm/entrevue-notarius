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
	
	public String geturlShortened() {
		return urlShortened;
	}

	public void seturlShortened(String urlShortened) {
		this.urlShortened = urlShortened;
	}

	public String getUrlOriginal() {
		return urlOriginal;
	}

	public void setUrlOriginal(String urlOriginal) {
		this.urlOriginal = urlOriginal;
	}
}
