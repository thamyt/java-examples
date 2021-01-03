package com.example.webclient.ejb.stateless;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class CurrencyBean2 implements CurrencyLocal {

	private HashMap<String, Float> currRates = new HashMap<String, Float>();
	
    /**
     * Default constructor. 
     */
    public CurrencyBean2() {
    	// prepare dummy data
    	currRates.put(String.format("SGD - (%s)", CurrencyBean2.class.getName()), 1.00f);
		currRates.put(String.format("MYR - (%s)", CurrencyBean2.class.getName()), 3.04f);
		currRates.put(String.format("USD - (%s)", CurrencyBean2.class.getName()), 1.35f);
		currRates.put(String.format("TWD - (%s)", CurrencyBean2.class.getName()), 22.45f);
		currRates.put(String.format("RMB - (%s)", CurrencyBean2.class.getName()), 5.05f);
		currRates.put(String.format("YEN - (%s)", CurrencyBean2.class.getName()), 79.56f);
		currRates.put(String.format("WON - (%s)", CurrencyBean2.class.getName()), 1000.05f);
		currRates.put(String.format("AUD - (%s)", CurrencyBean2.class.getName()), 0.977f);
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
	public float getcurrencyRate(String currencyName) {
		float rate = 0.0f;		
		if( currRates.containsKey(currencyName) ) {
			rate = currRates.get(currencyName);
		}
		return rate;
	}

}
