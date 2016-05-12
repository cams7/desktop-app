package br.com.cams7.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.cams7.domain.Category;

public class CategoryDAO {
	private EntityManagerFactory entityManagerFactory;

	public CategoryDAO() {
		entityManagerFactory = Persistence.createEntityManagerFactory("appUnit");
	}

	public void store(Category category) {
		EntityManager manager = entityManagerFactory.createEntityManager();
		EntityTransaction tx = manager.getTransaction();
		try {
			tx.begin();
			manager.merge(category);
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
			throw e;
		} finally {
			manager.close();
		}
	}

	public void delete(Long categoryId) {
		EntityManager manager = entityManagerFactory.createEntityManager();
		EntityTransaction tx = manager.getTransaction();
		try {
			tx.begin();
			Category category = manager.find(Category.class, categoryId);
			manager.remove(category);
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
			throw e;
		} finally {
			manager.close();
		}
	}

	public Category findById(Long categoryId) {
		EntityManager manager = entityManagerFactory.createEntityManager();
		try {
			return manager.find(Category.class, categoryId);
		} finally {
			manager.close();
		}
	}

	public List<Category> findAll() {
		EntityManager manager = entityManagerFactory.createEntityManager();
		try {
			Query query = manager.createQuery("SELECT c FROM Category c LEFT JOIN FETCH c.projects p");
			@SuppressWarnings("unchecked")
			List<Category> items = query.getResultList();

			return items;

		} finally {
			manager.close();
		}
	}

}
