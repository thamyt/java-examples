package com.example.springmvc_client.ejb.stateless;

import java.util.List;

import javax.ejb.Local;

@Local
public interface CurrencyLocal {
	public List<String> getCurrencyList();
	public float getCurrencyRate(String currencyName);
}
