package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {
	private static EntityManagerFactory factory;
	static {
		factory = Persistence.createEntityManagerFactory("dw_crypto_bd");

	}

	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}
}
