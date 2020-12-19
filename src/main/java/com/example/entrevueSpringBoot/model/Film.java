package com.example.entrevueSpringBoot.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;


/*
 * @author Allan Martins
 */
@Data
@Entity
@Table(name="films")
public class Film extends BaseEntity {
		
	@Column(name = "titre") // définir longueur
	private String titre;
	
	@Column(name = "description") // définir longueur
	private String description;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = false) 
	@JoinColumn(name="film_fk_id", referencedColumnName="id")
	@Column(name = "acteurs")
	private List<Acteur> acteurs;
}
