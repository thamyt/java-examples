package com.example.webclient.ejb.stateless;

import java.util.List;

import javax.ejb.Local;

@Local
public interface CurrencyLocal {
	List<String> getCurrencyList();
    float getcurrencyRate(String currencyName);
}
