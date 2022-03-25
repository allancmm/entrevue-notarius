package com.example.entrevueSpringBoot.model;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * @author Allan Martins
 */
@MappedSuperclass
public  class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	@CreationTimestamp
	@Column(name = "created_at", updatable = false, nullable = false)
	protected Instant createdAt;

	@UpdateTimestamp
	@Column(name = "modified_at")
	protected Instant modifiedAt;

	public Long getId() {
		return id;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

	public Instant getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Instant modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public void setId(Long id) {
		this.id = id;
	}	
}
