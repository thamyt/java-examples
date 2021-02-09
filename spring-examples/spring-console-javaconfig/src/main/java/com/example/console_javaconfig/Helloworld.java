package com.example.console_javaconfig;

public class Helloworld {
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public void printMessage() {
		System.out.println("The message is : " + message);
	}
}
