package dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import model.Moeda;

public class MoedaDAO implements Serializable {

	private static final long serialVersionUID = 5233375827420977754L;
	
	@Inject
	private EntityManager manager;

	public void save(Moeda moeda) throws Exception {
		try {
			manager.getTransaction().begin();
			if (moeda.getCodigo() == 0) {
				manager.persist(moeda);
			} else {
				if (!manager.contains(moeda)) {
					if (manager.find(Moeda.class, moeda.getCodigo()) == null) {
						throw new Exception("Erro ao atualizar a Moeda.");
					}
					moeda = manager.merge(moeda);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			manager.flush();
			manager.getTransaction().commit();
		}
	}
	
	public void delete(Moeda moeda) throws Exception {
		try {
			manager.getTransaction().begin();
			manager.remove(moeda);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Não foi possível remover a Moeda selecionada!");
		} finally {
			manager.flush();
			manager.getTransaction().commit();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Moeda> getAll() {
		return manager.createQuery("from Moeda").getResultList();
	}

}
