package com.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.security.controller", "com.security.model", "com.security.config"})
public class SpringSecurityBasicAuthentication3Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityBasicAuthentication3Application.class, args);
    }

}