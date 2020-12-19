package com.example.entrevueSpringBoot.dto.response;

import lombok.Data;

@Data
@SuppressWarnings("unused")
public class ActeurGetResponse {	
	private Long id;
	private String nom;
	private String prenom;
}
