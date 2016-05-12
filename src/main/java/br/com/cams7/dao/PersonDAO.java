package br.com.cams7.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.cams7.domain.Person;

public class PersonDAO {
	private EntityManagerFactory entityManagerFactory;

	public PersonDAO() {
		entityManagerFactory = Persistence.createEntityManagerFactory("appUnit");
	}

	public void store(Person person) {
		EntityManager manager = entityManagerFactory.createEntityManager();
		EntityTransaction tx = manager.getTransaction();
		try {
			tx.begin();
			manager.merge(person);
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
			throw e;
		} finally {
			manager.close();
		}
	}

	public void delete(Long personId) {
		EntityManager manager = entityManagerFactory.createEntityManager();
		EntityTransaction tx = manager.getTransaction();
		try {
			tx.begin();
			Person person = manager.find(Person.class, personId);
			manager.remove(person);
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
			throw e;
		} finally {
			manager.close();
		}
	}

	public Person findById(Long personId) {
		EntityManager manager = entityManagerFactory.createEntityManager();
		try {
			return manager.find(Person.class, personId);
		} finally {
			manager.close();
		}
	}

	public List<Person> findAll() {
		EntityManager manager = entityManagerFactory.createEntityManager();
		try {
			Query query = manager.createQuery("SELECT p FROM Person p LEFT JOIN FETCH p.projects");
			@SuppressWarnings("unchecked")
			List<Person> items = query.getResultList();

			return items;

		} finally {
			manager.close();
		}
	}

}