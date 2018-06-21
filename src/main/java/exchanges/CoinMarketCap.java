package exchanges;

import java.io.Serializable;
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
public class CoinMarketCap extends Exchange implements Serializable, ExchangeInterface {

	private static final long serialVersionUID = 1L;

	private ConsumerThread consumidor;

	private ObjectMapper mapper;

	private String cotacao;

	public CoinMarketCap() {
		super();
		enumExchange = EnumExchange.COIN_MARKET_CAP;
		mapper = new ObjectMapper();
		consumidor = new ConsumerThread("https://api.coinmarketcap.com/v1/ticker/", 5000, null);
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
			cotacao = consumidor.getCotacoes();
			cotacoes = mapper.readValue(cotacao,
					mapper.getTypeFactory().constructCollectionType(List.class, Cotacao.class));
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
