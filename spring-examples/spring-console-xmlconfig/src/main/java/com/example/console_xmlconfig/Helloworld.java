package com.example.console_xmlconfig;

public class Helloworld {
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public void printMessage() {
		System.out.println("[Helloworld] The message is : " + message);
	}
}
