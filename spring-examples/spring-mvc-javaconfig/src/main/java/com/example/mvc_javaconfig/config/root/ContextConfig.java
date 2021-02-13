package com.example.mvc_javaconfig.config.root;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContextConfig {

	@Bean 
	public Map<String, String> countryList() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("CA", "Canada");
        map.put("US", "United States");
        map.put("PK", "Pakistan");
        map.put("UE", "UAE");
        return map;
    }
}
