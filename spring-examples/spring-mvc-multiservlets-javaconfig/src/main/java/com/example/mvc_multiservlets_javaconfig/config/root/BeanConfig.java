package com.example.mvc_multiservlets_javaconfig.config.root;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.mvc_multiservlets_javaconfig.bean.HelloBean;

@Configuration
public class BeanConfig {

	@Bean(name = "helloBean")
	public HelloBean HelloworldSingleton() {
		System.out.println("Inside HelloworldSingleton..........");
		return new HelloBean();
	}
}
