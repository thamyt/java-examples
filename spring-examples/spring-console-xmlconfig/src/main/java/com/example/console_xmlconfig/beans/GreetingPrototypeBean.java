package com.example.console_xmlconfig.beans;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class GreetingPrototypeBean {
	private String message = "Good Day";

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public void printMessage() {
		System.out.println("[GreetingPrototypeBean] The message is : " + message);
	}
}
