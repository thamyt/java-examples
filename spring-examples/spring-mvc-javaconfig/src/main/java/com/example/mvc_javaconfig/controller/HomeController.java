package com.example.mvc_javaconfig.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.mvc_javaconfig.bean.HelloBean;
import com.example.mvc_javaconfig.dto.UserDTO;

@Controller
public class HomeController extends BaseController {
	
	@Autowired
	private UserDTO userDTO;
	
	@Autowired
	private HelloBean helloBean;
	
	@Resource(name="countryList")
	Map<String, String> ctrList;

	@RequestMapping(value="/", method = RequestMethod.GET)
	public String home(Model model) {
		
		ctrList.forEach((k, v) -> System.out.println((k + ":" + v)));
		
		model.addAttribute("helloMsg", helloBean);
		return "index.html";
	}
}
