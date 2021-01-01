package com.example.ejb.sessionbean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;

//@LocalBean
/**
 * Session Bean implementation class CurrencyExchange
 */
@Stateless
public class CurrencyExchange implements CurrencyExchangeRemote, CurrencyExchangeLocal {

	private HashMap<String, Float> currRates = new HashMap<String, Float>();
	
    /**
     * Default constructor. 
     */
    public CurrencyExchange() {
    	// prepare dummy data
    	currRates.put("SGD", 1.00f);
    	currRates.put("MYR", 3.04f);
    	currRates.put("USD", 1.35f);
    	currRates.put("TWD", 22.45f);
    	currRates.put("RMB", 5.05f);
    	currRates.put("YEN", 79.56f);
    	currRates.put("WON", 1000.05f);
    	currRates.put("AUD", 0.977f); 
    }

	@Override
	public float getcurrencyRate(String currencyName) {
		float rate = 0.0f;		
		if( currRates.containsKey(currencyName) ) {
			rate = currRates.get(currencyName);
		}
		return rate;
	}

	@Override
	public List<String> getCurrencyList() {
		List<String> result = new ArrayList<String>();
		
		for(String s : currRates.keySet()) {
			result.add(s);
		}
		return result;
	}

	@Override
	public Set<String> getCurrencySet() {
		Set<String> result = new HashSet<String>();		
		for(String s : currRates.keySet()) {
			result.add(s);
		}
		return result;
	}
}
