package com.entrevue.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.entrevue.model.Url;


@DataMongoTest
@ExtendWith(SpringExtension.class)
@AutoConfigureDataMongo
public class UrlRepositoryTest {

	private final String URL_DOCUMENT = "Url";
	
	@Test
	void itShouldSaveUrlShortened(@Autowired MongoTemplate mongoTemplate) {
		// given
		String urlShortened = "CY9rzUYh03";
		String urlOriginal = "www.amazon.com";
		Url url = new Url(urlShortened, urlOriginal);
		
		// when
		mongoTemplate.save(url, URL_DOCUMENT);
		
		// then		
		assertThat(mongoTemplate.count(new Query(), Url.class)).isEqualTo(1);
	}
		
	@Test
	public void itShouldFindByUrlShortened(@Autowired MongoTemplate mongoTemplate) {
		// given
		String urlShortened = "DtzJjunD0N";
		String urlOriginal = "www.google.com";
		Url url = new Url(urlShortened, urlOriginal);
		
		// when
		mongoTemplate.save(url, URL_DOCUMENT);
		
		// then		
		assertThat(mongoTemplate.find(new Query(Criteria.where("urlShortened").is(urlShortened)), Url.class)).isNotNull();
	}
	
}
