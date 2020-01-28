package com.springBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class SpringBootProgramApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootProgramApplication.class, args);
		
	}

}
