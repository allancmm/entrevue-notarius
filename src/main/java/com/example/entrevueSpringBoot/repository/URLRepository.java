package com.example.entrevueSpringBoot.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.example.entrevueSpringBoot.model.Url;

/*
 * @author Allan Martins
 */

@Repository
public interface URLRepository extends MongoRepository<Url, String> {

	public Optional<Url> findByurlShortened(String urlShortened);
}
