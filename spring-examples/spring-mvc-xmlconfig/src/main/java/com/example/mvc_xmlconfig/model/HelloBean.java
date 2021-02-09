package com.example.mvc_xmlconfig.model;

import org.springframework.stereotype.Component;

@Component
public class HelloBean {
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
