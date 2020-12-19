package com.example.entrevueSpringBoot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author Allan Martins
 */
@Data
@Entity
@Table(name = "acteurs")
public class Acteur extends BaseEntity {
	
	@Column(name = "nom") // définir longueur
	private String nom;
	
	@Column(name = "prenom") // définir longueur
	private String prenom;
}
