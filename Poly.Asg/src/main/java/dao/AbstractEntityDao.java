package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import utils.JpaUtils;

public abstract class AbstractEntityDao<EntityType> {
	private Class<EntityType> entityClass;

	public AbstractEntityDao(Class<EntityType> entityClass) {
		this.entityClass = entityClass;
	}

	public void insert(EntityType entity) {
		EntityManager eManager = JpaUtils.getEntityManager();

		EntityTransaction transaction = eManager.getTransaction();

		try {
			transaction.begin();

			eManager.persist(entity);

			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			eManager.close();
		}
	}

	public void update(EntityType entity) {
		EntityManager eManager = JpaUtils.getEntityManager();

		EntityTransaction transaction = eManager.getTransaction();

		try {
			transaction.begin();

			eManager.merge(entity);

			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			eManager.close();
		}
	}

	public void delete(Object id) {
		EntityManager eManager = JpaUtils.getEntityManager();

		EntityTransaction transaction = eManager.getTransaction();

		try {
			transaction.begin();

			EntityType entityType = eManager.find(entityClass, id);
			eManager.remove(entityType);

			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			eManager.close();
		}
	}

	public EntityType findById(Object id) {
		EntityManager eManager = JpaUtils.getEntityManager();

		try {
			EntityType entityType = eManager.find(entityClass, id);

			return entityType;
		} finally {
			eManager.close();
		}
	}

	public List<EntityType> findAll() {
		EntityManager eManager = JpaUtils.getEntityManager();
		try {
			CriteriaQuery criteriaQuery = eManager.getCriteriaBuilder().createQuery();

			criteriaQuery.select(criteriaQuery.from(entityClass));

			return eManager.createQuery(criteriaQuery).getResultList();
		} finally {
			eManager.close();
		}
	}

	public Long count() {
		EntityManager eManager = JpaUtils.getEntityManager();
		try {
			CriteriaQuery criteriaQuery = eManager.getCriteriaBuilder().createQuery();

			Root<EntityType> root = criteriaQuery.from(entityClass);
			criteriaQuery.select(eManager.getCriteriaBuilder().count(root));
			Query query = eManager.createQuery(criteriaQuery);
			return (Long) query.getSingleResult();
		} finally {
			eManager.close();
		}
	}

	public List<EntityType> findAll(boolean all, int firstResult, int maxResult) {
		EntityManager eManager = JpaUtils.getEntityManager();
		try {
			CriteriaQuery criteriaQuery = eManager.getCriteriaBuilder().createQuery();
			criteriaQuery.select(criteriaQuery.from(entityClass));
			Query query = eManager.createQuery(criteriaQuery);
			if (!all) {
				query.setFirstResult(firstResult);
				query.setMaxResults(maxResult);
			}
			return query.getResultList();
		} finally {
			eManager.close();
		}
	}
}
