package com.entrevue.model;

import java.util.Objects;

import javax.validation.constraints.NotBlank;

import lombok.*;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

/*
 * @author Allan Martins
 */
@Document(collection = "Url")
@Data
public class Url {

	@Id
	@NotBlank(message = "Url shortened cannot be null or empty")
	private String urlShortened;
	
	@NotBlank(message = "Url origin cannot be null or empty")
	@URL
	private String urlOriginal;
	
	public Url(String urlShortened, String urlOriginal) {
		this.urlShortened = Objects.requireNonNull(urlShortened);
		this.urlOriginal = Objects.requireNonNull(urlOriginal);
	}
}
