package exchanges;

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
public class Bittrex extends Exchange implements ExchangeInterface {

	private static final long serialVersionUID = 1L;

	private ConsumerThread consumidor;

	private ObjectMapper mapper;

	private String cotacao;
	
	private String cotacaoString;

	public Bittrex() {
		super();
		enumExchange = EnumExchange.BITTREX;
		mapper = new ObjectMapper();
		consumidor = new ConsumerThread("https://bittrex.com/api/v1.1/public/getmarketsummaries", 5000, null);
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
			cotacaoString = cotacaoString.replace("{\"success\":true,\"message\":\"\",\"result\":[{\"MarketName\":", "{");
			cotacaoString = cotacaoString.replace("{\"MarketName\":", "");
			cotacaoString = cotacaoString.replace("Last\":", "Last\":\"");
            cotacaoString = cotacaoString.replace(",\"BaseVolume\"", "\",\"BaseVolume\"");
            cotacaoString = cotacaoString.replace(",\"High\"", ":{\"High\"");
            cotacaoString = cotacaoString.replace("]", "");
			
			
			
			cotacaoString = cotacaoString.replace("Last", "price_btc");
			System.out.println(cotacaoString);
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
