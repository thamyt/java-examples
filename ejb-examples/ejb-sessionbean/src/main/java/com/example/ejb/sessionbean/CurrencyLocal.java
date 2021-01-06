package com.example.ejb.sessionbean;

import java.util.List;

import javax.ejb.Local;

@Local
public interface CurrencyLocal {
	void syncData();
	List<String> getCurrencyList();
    float getCurrencyRate(String currencyName);
}
