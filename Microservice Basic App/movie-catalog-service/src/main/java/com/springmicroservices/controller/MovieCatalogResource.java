package com.springmicroservices.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.ws.ResponseWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

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
	
	
	@GetMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable String userId) {

		/* RestTemplate restTemplate = new RestTemplate(); */

		UserRating ratings =
			//	restTemplate.getForObject("http://localhost:9099/rating/user/"+userId,
				
				restTemplate.getForObject("http://rating-data-service/rating/user/"+userId,
						UserRating.class);

		return ratings.getUserRating().stream().map(rating -> {
			
			//Movie movie = restTemplate.getForObject("http://localhost:9000/movie/" + rating.getMovieId(), Movie.class);
	
			Movie movie = restTemplate.getForObject("http://movie-info-service /movie/" + rating.getMovieId(), Movie.class);
			
//		Movie movie = builder.build()
//			.get()
//			.uri("http://localhost:9000/movie/" + rating.getMovieId())
//			.retrieve()
//			.bodyToMono(Movie.class)
//			.block();
			
			return new CatalogItem(movie.getName(), "Test", rating.getRating());
			
		}).collect(Collectors.toList());
	}

}
