package com.example.entrevueSpringBoot.model;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;


/**
 * @author Allan Martins
 */
@Data
@MappedSuperclass
public  class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	@CreationTimestamp
	@Column(name = "cree_a", updatable = false, nullable = false)
	protected Instant creeA;

	@UpdateTimestamp
	@Column(name = "modifie_a")
	protected Instant modifieA;

}
