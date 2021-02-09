package com.example.console_javaconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan("com.example.console_javaconfig.beans")
public class AppConfig {
	
	@Bean(name = "helloworldSingleton")
	public Helloworld HelloworldSingleton() {
		Helloworld obj = new Helloworld();
		obj.setMessage("Good Day");
		return obj;
	}
	
	@Bean(name = "helloworldPrototype")
	@Scope("prototype")
	public Helloworld HelloworldPrototype() {
		Helloworld obj = new Helloworld();
		obj.setMessage("Good Day");
		return obj;
	}
}
