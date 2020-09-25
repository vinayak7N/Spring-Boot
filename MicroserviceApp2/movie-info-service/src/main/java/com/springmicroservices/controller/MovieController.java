package com.springmicroservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.springmicroservices.model.Movie;
import com.springmicroservices.model.MovieSummary;

@RestController
@RequestMapping("/movie")
public class MovieController {

	/*
	 * static List<Movie> movies = Arrays.asList(
	 * 
	 * new Movie("101", "Harry Potter-1"), new Movie("102", "Transformer"), new
	 * Movie("103", "Fantastic Beast"));
	 */

	@Value("${api.key}")
	private String apiKey;

	@Autowired
	private RestTemplate restTemplate;

	/*
	 * @GetMapping public List<Movie> getAllMovies() {
	 * 
	 * return movies; }
	 */

	/*
	 * @GetMapping("/{movieId}") public Movie getMovieById(@PathVariable String
	 * movieId) { return movies.stream().filter(movie ->
	 * movie.getMovieId().equals(movieId)).findFirst().orElse(null); }
	 */

	@GetMapping(value ="/{movieId}", produces=MediaType.APPLICATION_JSON_VALUE)
	public Movie getMovieInfo(@PathVariable String movieId) {

		/*
		 * Connecting to external api themoviedb using api key
		 */
		MovieSummary movieSummary = restTemplate.getForObject(
				"https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + apiKey, MovieSummary.class);

		return new Movie(movieId, movieSummary.getTitle(), movieSummary.getOverview());
	}

}
