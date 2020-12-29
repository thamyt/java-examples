package com.example.ejb.sessionbean;

import java.util.List;
import java.util.Set;

import javax.ejb.Local;

@Local
public interface CurrencyExchangeLocal {
	List<String> getCurrencyList();
	Set<String> getCurrencySet();
    float getcurrencyRate(String currencyName);
}
