package persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import entity.EncurtadorURL;

public class EncurtadorURLDao {

	EntityManager manager;
	EntityTransaction transaction;

	static EntityManagerFactory factory = Persistence.createEntityManagerFactory("encurtadoURL");

	public EncurtadorURLDao() {
		manager = factory.createEntityManager();
	}

	public void create(EncurtadorURL url) throws Exception {
		transaction = manager.getTransaction();
		transaction.begin();
		manager.persist(url);
		transaction.commit();
	}

	public void update(EncurtadorURL url) throws Exception {
		transaction = manager.getTransaction();
		transaction.begin();
		manager.merge(url);
		transaction.commit();
	}

	public void delete(EncurtadorURL url) throws Exception {
		transaction = manager.getTransaction();
		transaction.begin();
		EncurtadorURL e = manager.merge(url);
		manager.remove(e);
		transaction.commit();
	}

	public List<EncurtadorURL> findAll() {
		return (List<EncurtadorURL>) manager.createQuery("select obj from EncurtadorURL as obj").getResultList();
	}

	public EncurtadorURL findByCode(Integer cod) {
		return manager.find(EncurtadorURL.class, cod);
	}

}