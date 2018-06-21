package dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import model.Usuario;

public class UsuarioDAO implements Serializable {

	private static final long serialVersionUID = 4757137274239024755L;
	
	@Inject
	private EntityManager manager;

	public void save(Usuario usuario) throws Exception {
		try {
			manager.getTransaction().begin();
			if (usuario.getCodigo() == 0) {
				manager.persist(usuario);
			} else {
				if (!manager.contains(usuario)) {
					if (manager.find(Usuario.class, usuario.getCodigo()) == null) {
						throw new Exception("Erro ao atualizar o Usuario.");
					}
					usuario = manager.merge(usuario);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			manager.flush();
			manager.getTransaction().commit();
		}
	}
	
	public void delete(Usuario usuario) throws Exception {
		try {
			manager.getTransaction().begin();
			manager.remove(usuario);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Não foi possível remover o Usuario selecionado!");
		} finally {
			manager.flush();
			manager.getTransaction().commit();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> getAll() {
		return manager.createQuery("from Usuario").getResultList();
	}
	
	public Usuario findUsuario(String login, String senha) {
		try {
			Query query = manager.createQuery("select distinct u from Usuario u "
					+ "where u.login = :LOGIN "
					+ "and u.senha = :SENHA ");
			query.setParameter("LOGIN", login);
			query.setParameter("SENHA", senha);
			return (Usuario) query.getSingleResult();
		}catch(NoResultException nre) {
			return null;
		}
	}
	
	public Boolean findEmailJaUsado(String email) {
		try {
			Query query = manager.createQuery("select count(u) from Usuario u "
					+ "where u.email = :EMAIL");
			query.setParameter("EMAIL", email);
			return ((Long) query.getSingleResult()).intValue() > 0;
		}catch(NoResultException nre) {
			return false;
		}
	}
	
	public Boolean findLoginJaUsado(String login) {
		try {
			Query query = manager.createQuery("select count(u) from Usuario u "
					+ "where u.login = :LOGIN");
			query.setParameter("LOGIN", login);
			return ((Long) query.getSingleResult()).intValue() > 0;
		}catch(NoResultException nre) {
			return false;
		}
	}

}
