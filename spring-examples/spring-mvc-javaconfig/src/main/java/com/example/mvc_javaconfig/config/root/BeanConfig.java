package com.example.mvc_javaconfig.config.root;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.mvc_javaconfig.bean.AppProp;
import com.example.mvc_javaconfig.bean.HelloBean;

@Configuration
public class BeanConfig {

	@Bean(name = "helloBean")
	public HelloBean HelloworldSingleton() {
		System.out.println("Inside HelloworldSingleton..........");
		return new HelloBean();
	}
	
	@Bean(name = "appProp")
	public AppProp AppPropSingleton() {
		System.out.println("Inside AppPropSingleton..........");
		return new AppProp();
	}
}
