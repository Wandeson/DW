package webService;

import java.util.List;

import model.Cotacao;
import model.Moeda;

public interface ExchangeInterface {

	public abstract void convertJsonTicker();

	public abstract List<Cotacao> getVolume(Moeda moeda1, Moeda moeda2);

	public abstract void convertJsonVolume();

	public abstract List<Cotacao> getTicket();

}
