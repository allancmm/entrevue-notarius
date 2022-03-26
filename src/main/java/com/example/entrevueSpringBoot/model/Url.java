package com.example.entrevueSpringBoot.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

/*
 * @author Allan Martins
 */
@Document(collection = "Url")
public class Url {

	@Id
	private String urlShortned;
	
	private String urlOriginal;
	
	public String getUrlShortned() {
		return urlShortned;
	}

	public void setUrlShortned(String urlShortned) {
		this.urlShortned = urlShortned;
	}

	public String getUrlOriginal() {
		return urlOriginal;
	}

	public void setUrlOriginal(String urlOriginal) {
		this.urlOriginal = urlOriginal;
	}
}
