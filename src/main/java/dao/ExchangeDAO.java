package dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import model.Exchange;

public class ExchangeDAO implements Serializable {

	private static final long serialVersionUID = -3424933181628784128L;
	
	@Inject
	private EntityManager manager;

	public void save(Exchange exchange) throws Exception {
		try {
			manager.getTransaction().begin();
			if (exchange.getCodigo() == 0) {
				manager.persist(exchange);
			} else {
				if (!manager.contains(exchange)) {
					if (manager.find(Exchange.class, exchange.getCodigo()) == null) {
						throw new Exception("Erro ao atualizar a Exchange.");
					}
					exchange = manager.merge(exchange);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			manager.flush();
			manager.getTransaction().commit();
		}
	}
	
	public void delete(Exchange exchange) throws Exception {
		try {
			manager.getTransaction().begin();
			manager.remove(exchange);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Não foi possível remover a Exchange selecionada!");
		} finally {
			manager.flush();
			manager.getTransaction().commit();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Exchange> getAll() {
		return manager.createQuery("from Exchange").getResultList();
	}

}
