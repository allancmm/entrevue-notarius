package com.example.entrevueSpringBoot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/*
 * @author Allan Martins
 */
@Entity
@Table(name="url")
public class Url extends BaseEntity {

	@Column(name = "urlShortned")
	private String urlShortned;
	
	@Column(name = "urlOriginal")
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
