package com.example.mvc_xmlconfig.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.mvc_xmlconfig.bean.AppProp;

public class BaseController {
	@Autowired
	private AppProp appProp;
	
	@ModelAttribute("title")
	public String getTitle() {
		return appProp.getAppTitle();
	}
}
