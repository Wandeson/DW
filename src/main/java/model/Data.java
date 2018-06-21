package model;

import java.math.BigDecimal;
import java.util.HashMap;

public class Data {

	private String currency;
	
	private HashMap<String, BigDecimal> rates;

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public HashMap<String, BigDecimal> getRates() {
		return rates;
	}

	public void setRates(HashMap<String, BigDecimal> rates) {
		this.rates = rates;
	}

}
