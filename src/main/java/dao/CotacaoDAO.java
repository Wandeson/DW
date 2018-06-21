package dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import model.Cotacao;

public class CotacaoDAO implements Serializable {
	
	private static final long serialVersionUID = -1852957861668342588L;
	
	@Inject
	private EntityManager manager;

	public void save(Cotacao cotacao) throws Exception {
		try {
			manager.getTransaction().begin();
			if (cotacao.getCodigo() == 0) {
				manager.persist(cotacao);
			} else {
				if (!manager.contains(cotacao)) {
					if (manager.find(Cotacao.class, cotacao.getCodigo()) == null) {
						throw new Exception("Erro ao atualizar a Cotacao.");
					}
					cotacao = manager.merge(cotacao);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			manager.flush();
			manager.getTransaction().commit();
		}
	}
	
	public void delete(Cotacao cotacao) throws Exception {
		try {
			manager.getTransaction().begin();
			manager.remove(cotacao);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Não foi possível remover a Cotacao selecionada!");
		} finally {
			manager.flush();
			manager.getTransaction().commit();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Cotacao> getAll() {
		return manager.createQuery("from Cotacao").getResultList();
	}

}
