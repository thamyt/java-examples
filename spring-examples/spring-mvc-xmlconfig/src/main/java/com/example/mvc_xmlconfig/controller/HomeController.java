package com.example.mvc_xmlconfig.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.mvc_xmlconfig.bean.HelloBean;

@Controller
@RequestMapping("/")
public class HomeController extends BaseController {
	
	@Autowired
	private HelloBean helloBean;
	
	@Autowired
	BCryptPasswordEncoder encoder;
	
	@Resource(name="countryList")
	Map<String, String> ctrList;

	@RequestMapping(method = RequestMethod.GET)
	public String home(Model model) {
		ctrList.forEach((k, v) -> System.out.println((k + ":" + v)));
		
		System.out.println(encoder.encode("P@ssw0rd123"));
		System.out.println(encoder.encode("P@ssw0rd123"));
		
		model.addAttribute("helloMsg", helloBean);
		return "index.html";
	}
}