package com.springmicroservices.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.springmicroservices.model.CatalogItem;
import com.springmicroservices.model.Movie;
import com.springmicroservices.model.Rating;
import com.springmicroservices.model.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

	@Autowired
	private RestTemplate restTemplate;

	/*
	 * @Autowired private WebClient.Builder builder;
	 */

	static List<CatalogItem> catalogList = Arrays.asList(

			new CatalogItem("Transformers", "Transformers movie", 4),
			new CatalogItem("Harry Potter Series", "Harry Potter collection movies", 5),
			new CatalogItem("GOT Series", "GOT series", 3));

	@GetMapping
	public List<CatalogItem> getCatalogs() {

		return catalogList;
	}

	@GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	//@HystrixCommand(fallbackMethod = "getFallbackCatalog")
	public List<CatalogItem> getCatalog(@PathVariable String userId) {

		/* RestTemplate restTemplate = new RestTemplate(); */

		UserRating ratings = getUserRating(userId);

		return ratings.getUserRating().stream().map(rating -> {

			return getCatalogItem(rating);

		}).collect(Collectors.toList());
	}
	
	@HystrixCommand(fallbackMethod = "getFallbackCatalogItem")
	private CatalogItem getCatalogItem(Rating rating) {

		// Movie movie = restTemplate.getForObject("http://localhost:9000/movie/" +
		// rating.getMovieId(), Movie.class);

		Movie movie = restTemplate.getForObject("http://movie-info-service/movie/" + rating.getMovieId(), Movie.class);

		// Movie movie = builder.build()
		// .get()
		// .uri("http://localhost:9000/movie/" + rating.getMovieId())
		// .retrieve()
		// .bodyToMono(Movie.class)
		// .block();

		return new CatalogItem(movie.getName(), movie.getOverview(), rating.getRating());
	}
	
	private CatalogItem getFallbackCatalogItem(Rating rating) {
		 return new CatalogItem("Movie name not found!","Not found",rating.getRating());
	}

	@HystrixCommand(fallbackMethod = "getFallbackUserRating")
	private UserRating getUserRating(String userId) {
		return restTemplate.getForObject("http://rating-data-service/rating/user/" + userId, UserRating.class);
	}

	private UserRating getFallbackUserRating(String userId) {
		UserRating userRating = new UserRating();
		userRating.setUserRating(Arrays.asList(
				new Rating("0",1)));
		return userRating;
	}
	
	private  List<CatalogItem> getFallbackCatalog(@PathVariable String userId) {
		return Arrays.asList(new CatalogItem("No Movie", "", 0));
	}

}

