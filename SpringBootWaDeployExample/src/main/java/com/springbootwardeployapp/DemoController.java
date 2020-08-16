package com.springbootwardeployapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping
    public String demoCall() {
        return "Simple project to build and deploy Spring Boot app as war in server";
    }


}
