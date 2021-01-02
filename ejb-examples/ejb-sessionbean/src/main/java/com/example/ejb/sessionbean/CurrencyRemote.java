package com.example.ejb.sessionbean;

import java.util.List;
import java.util.Set;

import javax.ejb.Remote;

@Remote
public interface CurrencyRemote {
	List<String> getCurrencyList();
    float getcurrencyRate(String currencyName);
}
