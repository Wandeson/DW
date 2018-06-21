package exchanges;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import enuns.EnumExchange;
import model.Cotacao;
import model.Data;
import model.Exchange;
import model.Moeda;
import reflection.ExchangeAnnotation;
import webService.ConsumerThread;
import webService.ExchangeInterface;

@ExchangeAnnotation
public class CoinBase extends Exchange implements Serializable, ExchangeInterface {

	private static final long serialVersionUID = 1L;

	private ConsumerThread consumidor;

	private ObjectMapper mapper;

	private String cotacaoString;

	public CoinBase() {
		super();
		enumExchange = EnumExchange.COINBASE;
		mapper = new ObjectMapper();
		consumidor = new ConsumerThread("https://api.coinbase.com/v2/exchange-rates?currency=BTC", 5000, null);
		consumidor.start();

	}

	@Override
	public void convertJsonTicker() {
		try {
			cotacaoString = consumidor.getCotacoes();

			HashMap<String, Data> result = mapper.readValue(cotacaoString,
					mapper.getTypeFactory().constructMapType(HashMap.class, String.class, Data.class));

			HashMap<String, BigDecimal> rates = result.get("data").getRates();
			
			for(String symbol : rates.keySet()) {
				
				Cotacao cot = new Cotacao();
				cot.setSymbol(symbol);
				cot.setPrice_btc(rates.get(symbol));
				
				cotacoes.add(cot);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Cotacao> getVolume(Moeda moeda1, Moeda moeda2) {
		return null;
	}

	@Override
	public void convertJsonVolume() {

	}

	@Override
	public List<Cotacao> getTicket() {
		convertJsonTicker();
		return cotacoes;
	}

}
