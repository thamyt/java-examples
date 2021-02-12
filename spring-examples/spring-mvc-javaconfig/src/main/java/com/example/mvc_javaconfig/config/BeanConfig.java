package com.example.mvc_javaconfig.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.mvc_javaconfig.model.HelloBean;

@Configuration
public class BeanConfig {

	@Bean(name = "helloBean")
	public HelloBean HelloworldSingleton() {
		System.out.println("Inside HelloworldSingleton..........");
		return new HelloBean();
	}
}
