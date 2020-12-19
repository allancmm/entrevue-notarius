package com.example.entrevueSpringBoot.dto.response;

import java.util.List;

import lombok.Data;

@Data
@SuppressWarnings("unused")
public class FilmGetResponse {
	private Long id;
	private String titre;	
	private String description;	
	private List<ActeurGetResponse> acteurs;
}
