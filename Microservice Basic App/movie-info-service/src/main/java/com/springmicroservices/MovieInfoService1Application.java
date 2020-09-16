package com.springmicroservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MovieInfoService1Application {

	public static void main(String[] args) {
		SpringApplication.run(MovieInfoService1Application.class, args);
	}
	//server.port=9000
}
