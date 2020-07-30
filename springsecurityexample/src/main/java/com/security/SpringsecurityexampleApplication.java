package com.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.security.controller","com.security.model"})
public class SpringsecurityexampleApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringsecurityexampleApplication.class, args);
    }

}
