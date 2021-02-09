package com.example.console_javaconfig.beans;

import org.springframework.stereotype.Component;

@Component
public class GreetingSingletonBean {
	private String message = "Good Day";

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public void printMessage() {
		System.out.println("[GreetingSingletonBean] The message is : " + message);
	}
}
