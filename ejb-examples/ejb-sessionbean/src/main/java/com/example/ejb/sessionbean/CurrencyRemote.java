package com.example.ejb.sessionbean;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface CurrencyRemote {
	List<String> getCurrencyList();
    float getCurrencyRate(String currencyName);
}
