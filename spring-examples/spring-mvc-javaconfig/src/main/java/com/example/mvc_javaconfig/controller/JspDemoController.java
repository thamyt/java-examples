package com.example.mvc_javaconfig.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.mvc_javaconfig.model.UserBean;

@Controller
@RequestMapping("/jspdemo")
public class JspDemoController {
	
	@RequestMapping(value="", method = RequestMethod.GET)
	public String home(Model model) {
		return "jsp/demo";
	}
	
	@RequestMapping(value="/loop", method = RequestMethod.GET)
	public String loop(Model model) {
		List<UserBean> users = new ArrayList<UserBean>();
		
		users.add(new UserBean("Mary","Tan", Date.valueOf("1975-12-12"), 1.75f));
		users.add(new UserBean("Peter","Wong", Date.valueOf("1980-12-12"), 1.65f));
		users.add(new UserBean("James","Lim", Date.valueOf("1985-12-12"), 1.85f));
		users.add(new UserBean("Jenny","Lee", Date.valueOf("1983-12-12"), 1.70f));
		
		model.addAttribute("users", users);
		
		return "jsp/loop";
	}
	
	
}
