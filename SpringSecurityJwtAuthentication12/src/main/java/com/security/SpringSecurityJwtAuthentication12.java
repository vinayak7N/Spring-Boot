package com.security;

import com.security.jwt.JwtConfig;
import com.security.jwt.JwtSecretKey;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.security.controller", "com.security.model", "com.security.config", "com.security.auth", "com.security.jwt"})
@EnableConfigurationProperties({JwtConfig.class})
public class SpringSecurityJwtAuthentication12 {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityJwtAuthentication12.class, args);
    }
}
