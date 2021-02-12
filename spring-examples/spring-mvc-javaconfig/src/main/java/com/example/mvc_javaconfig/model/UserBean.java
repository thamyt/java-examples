package com.example.mvc_javaconfig.model;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class UserBean {
	private String firstName;
	private String lastName;
	private Date dob;
	private float height;
	
	public UserBean(String firstName, String lastName, Date dob, float height) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.height = height;
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
