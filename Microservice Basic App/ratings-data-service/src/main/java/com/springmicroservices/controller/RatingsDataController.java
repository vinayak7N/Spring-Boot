package com.springmicroservices.controller;

import java.util.Arrays;
import java.util.List;

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

	@GetMapping("/user/{userId}")
	public UserRating getUserRating(@PathVariable String userId) {

		List<Rating> ratings = Arrays.asList(new Rating("101", 4), new Rating("102", 3),new Rating("103",5));
		UserRating userRating = new UserRating();
		userRating.setUserRating(ratings);
		return userRating;
	}

}
