package com.example.mvc_javaconfig.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.mvc_javaconfig.bean.AppProp;

public class BaseController {
	@Autowired
	private AppProp appProp;
	
	@ModelAttribute("title")
	public String getTitle() {
		return appProp.getAppTitle();
	}
}
