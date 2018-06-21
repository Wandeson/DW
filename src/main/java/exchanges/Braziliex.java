package exchanges;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import enuns.EnumExchange;
import model.Cotacao;
import model.Exchange;
import model.Moeda;
import reflection.ExchangeAnnotation;
import webService.ConsumerThread;
import webService.ExchangeInterface;

@ExchangeAnnotation
public class Braziliex extends Exchange implements Serializable, ExchangeInterface {

	private static final long serialVersionUID = 1L;

	private ConsumerThread consumidor;

	private ObjectMapper mapper;

	private String cotacaoString;

	public Braziliex() {
		super();
		enumExchange = EnumExchange.BRAZILIEX;
		mapper = new ObjectMapper();
		consumidor = new ConsumerThread("https://braziliex.com/api/v1/public/ticker", 5000, null);
		consumidor.start();
	}

	public ConsumerThread getConsumidor() {
		return consumidor;
	}

	public void setConsumidor(ConsumerThread consumidor) {
		this.consumidor = consumidor;
	}

	@Override
	public void convertJsonTicker() {
		try {
			cotacaoString = consumidor.getCotacoes();
			cotacaoString = cotacaoString.replace("last", "price_btc");

			HashMap<String, Cotacao> result = mapper.readValue(cotacaoString,
					mapper.getTypeFactory().constructMapType(HashMap.class, String.class, Cotacao.class));

			for (String key : result.keySet()) {
				Cotacao cot = result.get(key);
				cot.setSymbol(key);
				cotacoes.add(cot);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Cotacao> getVolume(Moeda moeda1, Moeda moeda2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void convertJsonVolume() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Cotacao> getTicket() {
		convertJsonTicker();
		return cotacoes;
	}

}
