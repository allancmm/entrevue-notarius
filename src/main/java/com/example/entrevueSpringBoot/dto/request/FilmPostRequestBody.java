package com.example.entrevueSpringBoot.dto.request;

import java.util.List;

import lombok.Data;

@Data
@SuppressWarnings("unused")
public class FilmPostRequestBody {
	private String titre;	
	private String description;	
	private List<ActeurPostRequestBody> acteurs;
}
