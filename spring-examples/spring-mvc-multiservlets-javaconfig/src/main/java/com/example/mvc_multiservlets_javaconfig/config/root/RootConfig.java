package com.example.mvc_multiservlets_javaconfig.config.root;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = { "com.example.mvc_multiservlets_javaconfig.dto" })
@Import({ BeanConfig.class,
	      ContextConfig.class,
	      PropertyConfig.class
	    })
public class RootConfig {
}
