package com.ms.ratingservice;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ms.ratingservice.entities.Rating;
import com.ms.ratingservice.service.RatingService;

@SpringBootTest
class RatingserviceApplicationTests {

	@Autowired
	private RatingService ratingService;

	@Test
	void contextLoads() {
	}

	@Test
	void createRating() {
		Rating rating = Rating.builder().rating(10).userId("").hotelId("").feedback("Test. Created using feign client.")
				.build();
		Rating savedRating = ratingService.createRating(rating);

		Logger logger = LoggerFactory.getLogger(RatingserviceApplicationTests.class);
		logger.info("TEST", savedRating);

	}

}
