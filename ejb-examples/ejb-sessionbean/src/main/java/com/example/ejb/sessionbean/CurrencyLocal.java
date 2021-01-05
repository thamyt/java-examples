package com.example.ejb.sessionbean;

import java.util.List;
import java.util.Set;

import javax.ejb.Local;

@Local
public interface CurrencyLocal {
	List<String> getCurrencyList();
    float getCurrencyRate(String currencyName);
}
