package com.example.webclient.controller;

import javax.ejb.EJB;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.ejb.sessionbean.CurrencyExchangeLocal;

@Controller
public class HomeController {
	
	@EJB(lookup = "java:global/classes/CurrencyExchange")
	CurrencyExchangeLocal currencyExchangeLocal;

	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model) {
		
		// dummy currency
		/*
		List<String> currencies = new ArrayList<String>();
		currencies.add("SGD");
		currencies.add("MYR");
		currencies.add("USD");
		currencies.add("RMB");
		currencies.add("YEN");
		currencies.add("TWD");
		*/
		
		model.addAttribute("currencies", currencyExchangeLocal.getCurrencyList());
		
        return "index";
    }
	
	
	@RequestMapping(value = "/currencyrate", method = RequestMethod.GET)
    public String getCurrencyRate(Model model) {
        return "index";
    }
}
