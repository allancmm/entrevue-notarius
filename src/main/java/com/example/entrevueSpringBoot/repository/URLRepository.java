package com.example.entrevueSpringBoot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.entrevueSpringBoot.model.Url;

@Repository
public interface URLRepository extends JpaRepository<Url, String> {

	public Optional<Url> findByUrlShortned(String urlShortned);
}
