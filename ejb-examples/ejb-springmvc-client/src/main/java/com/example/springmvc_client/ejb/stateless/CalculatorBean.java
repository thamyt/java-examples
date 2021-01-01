package com.example.springmvc_client.ejb.stateless;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class CalculatorBean implements CalculatorLocal {
	
	@Override
	public List<String> getCurrencies() {
		List<String> result = new ArrayList<String>();
		
		result.add("SGD");
		result.add("MYR");
		result.add("USD");
		
		return result;
	}

	@Override
	public double add(double a, double b) {
		return a+b;
	}

	@Override
	public double substract(double a, double b) {
		// TODO Auto-generated method stub
		return a-b;
	}

	@Override
	public double multiply(double a, double b) {
		return a*b;
	}

	@Override
	public double divide(double a, double b) {
		return a/b;
	}
}
