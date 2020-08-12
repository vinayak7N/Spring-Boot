package com.springjdbc.springjdbcproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@ComponentScan(basePackages={"com.springjdbc.springjdbcproject.controller","com.springjdbc.springjdbcproject.repository"})
@SpringBootApplication
public class SpringjdbcprojectApplication {


    public static void main(String[] args) {
        SpringApplication.run(SpringjdbcprojectApplication.class, args);
    }

}
