package com.example.ejb.sessionbean;

import java.util.List;
import java.util.Set;

import javax.ejb.Remote;

@Remote
public interface CurrencyExchangeRemote {
	List<String> getCurrencyList();
	Set<String> getCurrencySet();
    float getcurrencyRate(String currencyName);
}
