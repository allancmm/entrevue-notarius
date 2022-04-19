package com.entrevue.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;


@DataMongoTest
@ExtendWith(SpringExtension.class)
@TestPropertySource(properties = "spring.mongodb.embedded.version=3.5.5")
public class UrlRepositoryTest {

	@Test
	public void itShouldFindByUrlShortened(@Autowired MongoTemplate mongoTemplate) {
		// give
		DBObject objectToSave = BasicDBObjectBuilder.start().add("urlShortened", "test").get();
		
		// when
		mongoTemplate.save(objectToSave, "Url");
		
		// then
		assertThat(mongoTemplate.findAll(DBObject.class, "Url")).extracting("urlShortened").containsOnly("test");
	}
	
}
