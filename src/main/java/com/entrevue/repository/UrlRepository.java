package com.entrevue.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.entrevue.model.Url;

/*
 * @author Allan Martins
 */

@Repository
public interface UrlRepository extends MongoRepository<Url, String> {

	Optional<Url> findByUrlShortened(String urlShortened);
}
