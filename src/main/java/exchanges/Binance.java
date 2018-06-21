package exchanges;

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
public class Binance extends Exchange implements ExchangeInterface {

	private static final long serialVersionUID = 1L;

	private ConsumerThread consumidor;

	private ObjectMapper mapper;

	private String cotacao;

	public Binance() {
		super();
		enumExchange = EnumExchange.BINANCE;
		mapper = new ObjectMapper();
		consumidor = new ConsumerThread("https://api.binance.com/api/v3/ticker/price", 5000, null);
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
			cotacao = cotacao.replace("price", "price_btc");
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
