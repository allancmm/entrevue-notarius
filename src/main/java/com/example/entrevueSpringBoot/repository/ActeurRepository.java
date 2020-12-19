package com.example.entrevueSpringBoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entrevueSpringBoot.model.Acteur;

@Repository
public interface ActeurRepository extends JpaRepository<Acteur, Long>{}
