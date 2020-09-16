package com.springmicroservices.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springmicroservices.model.Movie;

@RestController
@RequestMapping("/movie")
public class MovieController {

	static List<Movie> movies = Arrays.asList(
			
			new Movie("101", "Harry Potter-1"), 
			new Movie("102", "Transformer"),
			new Movie("103", "Fantastic Beast"));

	@GetMapping
	public List<Movie> getAllMovies() {

		return movies;
	}

	@GetMapping("/{movieId}")
	public Movie getMovieById(@PathVariable String movieId) {
		return movies.stream().filter(movie -> movie.getMovieId().equals(movieId)).findFirst().orElse(null);
	}

}
