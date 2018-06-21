package services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import dao.ExchangeDAO;
import model.Exchange;

public class ExchangeServices implements Serializable {

	private static final long serialVersionUID = 4946572995923273538L;
	
	@Inject
	private ExchangeDAO exchangeDAO;

	public void save(Exchange exchange) throws Exception {
		exchangeDAO.save(exchange);
	}

	public void delete(Exchange exchange) throws Exception {
		exchangeDAO.delete(exchange);
	}

	public List<Exchange> getAll(){
		return exchangeDAO.getAll();
	}

}
