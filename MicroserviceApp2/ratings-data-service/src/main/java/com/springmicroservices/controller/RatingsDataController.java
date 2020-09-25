package com.springmicroservices.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springmicroservices.model.Rating;
import com.springmicroservices.model.UserRating;

@RestController
@RequestMapping("/rating")
public class RatingsDataController {

	@GetMapping
	public String get() {
		System.out.println("Method called..............");
		return "Test";
	}

	@GetMapping("/{movieId}")
	public Rating getRating(@PathVariable String movieId) {
		return new Rating(movieId, 4);
	}

	@GetMapping(value="/user/{userId}", produces=MediaType.APPLICATION_JSON_VALUE)
	public UserRating getUserRating(@PathVariable String userId) {

		List<Rating> ratings = Arrays.asList(
				new Rating("550", 4), new Rating("551", 3),new Rating("552",5));
		UserRating userRating = new UserRating();
		userRating.setUserRating(ratings);
		return userRating;
	}

}
