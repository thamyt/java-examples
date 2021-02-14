package com.example.mvc_multiservlets_xmlconfig.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class UserDTO {
	private String firstName;
	private String lastName;
	private Date dob;
	private float height;
	
	public UserDTO add(String firstName, String lastName, Date dob, float height) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.height = height;
		return this;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
}
