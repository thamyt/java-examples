package com.example.springmvc_client.ejb.stateless;

import java.util.List;

import javax.ejb.Local;

@Local
public interface CalculatorLocal {
	
	double add(double a, double b);
	double substract(double a, double b);
	double multiply(double a, double b);
	double divide(double a, double b);
	List<String> getCurrencies();
}
