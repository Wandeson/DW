package services;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import dao.CotacaoDAO;
import enuns.EnumExchange;
import model.Cotacao;
import model.Exchange;
import reflection.ReflectionClassesFinder;

public class CotacaoServices implements Serializable {

	private static final long serialVersionUID = -6272766871533160341L;

	private HashMap<EnumExchange, Exchange> consumidores;

	private ReflectionClassesFinder finder;

	@Inject
	private CotacaoDAO cotacaoDAO;

	public CotacaoServices() {
		consumidores = new HashMap<>();

		finder = new ReflectionClassesFinder();

		for (Class<?> classe : finder.getExchanges()) {

			// cria um objeto para cada classe anotada
			Exchange exchange = (Exchange) ReflectionClassesFinder.createNewInstance(classe);
			// add o objeto no hashmap usando o enum como chave
			consumidores.put(exchange.getEnumExchange(), exchange);
			System.out.println(exchange.getEnumExchange());
		}

	}

	// pega a informacao(ticker) de um consumidor especifico
	public List<Cotacao> get(EnumExchange exchange) {
		return consumidores.get(exchange).getTicket();
	}

	public void save(Cotacao cotacao) throws Exception {
		cotacaoDAO.save(cotacao);
	}

	public void delete(Cotacao cotacao) throws Exception {
		cotacaoDAO.delete(cotacao);
	}

	public List<Cotacao> getAll() {
		return cotacaoDAO.getAll();
	}

}
