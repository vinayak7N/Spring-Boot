package com.springBoot.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springBoot.filter.MyNewFilter;

@Configuration
public class FilterConfig {

	@Bean
	public FilterRegistrationBean<MyNewFilter> registrationBean() {
		FilterRegistrationBean<MyNewFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new MyNewFilter());
		registrationBean.addUrlPatterns("/employees/*");
		return registrationBean;
	}
}
