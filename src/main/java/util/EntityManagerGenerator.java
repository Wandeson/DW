package util;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerGenerator {
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("dw_crypto_bd");

	@Produces
	@RequestScoped
	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}

	public void finaliza(@Disposes EntityManager manager) {
		manager.close();
	}
}
