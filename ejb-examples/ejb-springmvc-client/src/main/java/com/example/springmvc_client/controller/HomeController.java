package com.example.springmvc_client.controller;

import javax.ejb.EJB;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.ejb.sessionbean.CurrencyRemote;
import com.example.springmvc_client.ejb.stateless.CalculatorLocal;
import com.example.springmvc_client.ejb.stateless.CurrencyLocal;

@Controller
public class HomeController {
	
	@EJB
	private CalculatorLocal calculator;
	
	@EJB
	CurrencyLocal currencyLocal;
	
	@EJB
	CurrencyRemote currencyRemote;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		
		/*
		Context context;
		try {
			System.out.println("Preparing initial context...");
			context = new InitialContext();
			
			System.out.println("context lookup for remote EJB ...");
			CurrencyExchangeRemote currencyExchange = (CurrencyExchangeRemote) context.lookup("ejb:/ejb-sessionbean-0.0.1-SNAPSHOT/CurrencyExchange!"
														+ CurrencyExchangeRemote.class.getName());
			System.out.println("get the currency list...");
			model.addAttribute("remoteCurrencies", currencyExchange.getCurrencyList());
			
			System.out.println("context lookup for local EJB ...");
			CalculaterLocal calculaterLocal = (CalculaterLocal) context.lookup("java:global/ejb-web-client/Calculater!"
												+ CalculaterLocal.class.getName());
			System.out.println("get the currency list...");
			model.addAttribute("localCurrencies", calculaterLocal.getCurrencies());
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		return "index";
	}
	
	@RequestMapping(value = "/calculator", method = RequestMethod.GET)
	public String calculator(Model model) {
		return "calculator";
	}
	
	@RequestMapping(value = "/currency", method = RequestMethod.GET)
	public String currency(Model model) {
		
		model.addAttribute("localCurrencies", currencyLocal.getCurrencyList());
		model.addAttribute("remoteCurrencies", currencyRemote.getCurrencyList());
		
		return "currency";
	}
	
	@RequestMapping(value = "/calculator", method = RequestMethod.POST)
	public String calculator(Model model,
							 @RequestParam String value1, 
							 @RequestParam String value2,
							 @RequestParam String operand) {
		
		System.out.println("Processing calculator submit");
		
		if( value1.isEmpty() || value2.isEmpty()) {
			model.addAttribute("error", "Please enter the value to compute!");
		}
		else if( operand.length() > 1 ) {
			model.addAttribute("error", "Please select the operand!");
		}
		else {
			try {
				double result = 0.0;
				double a = Double.parseDouble(value1);
				double b = Double.parseDouble(value2);
				
				switch(operand) {
					case "+":	result = calculator.add(a, b); break;
					case "-":	result = calculator.substract(a, b); break;
					case "*":	result = calculator.multiply(a, b); break;
					case "/":	result = calculator.divide(a, b); break;				
				}
				
				model.addAttribute("result", result);
			}
			catch(Exception e) {
				model.addAttribute("error", e.getMessage());
			}
		}
		
		System.out.println("returning value");
	
		return "calculator";
	}
	
	@RequestMapping(value = "/currency", method = RequestMethod.POST)
	public String currency(Model model,
						   @RequestParam String localCurrency, 
						   @RequestParam String remoteCurrency) {
		
		System.out.println("localCurrency : " + localCurrency);
		System.out.println("remoteCurrency : " + remoteCurrency);
		
		// set the attribute for the submitted parameter to prevent values cleared on page load 
		model.addAttribute("localCurrency", localCurrency);
		model.addAttribute("remoteCurrency", remoteCurrency);
				
		model.addAttribute("localRate", currencyLocal.getCurrencyRate(localCurrency));
		model.addAttribute("remoteRate", currencyRemote.getCurrencyRate(remoteCurrency));
		
		return currency(model);
	}
}
