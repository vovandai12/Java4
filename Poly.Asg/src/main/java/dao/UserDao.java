package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import model.User;
import utils.JpaUtils;

public class UserDao extends AbstractEntityDao<User> {

	public UserDao() {
		super(User.class);
	}

	public void changePassword(String username, String password, String newPassword) {
		EntityManager eManager = JpaUtils.getEntityManager();

		EntityTransaction transaction = eManager.getTransaction();

		String jpql = "select u from User u where u.username = :username and u.password = :password";

		try {
			transaction.begin();

			TypedQuery<User> query = eManager.createQuery(jpql, User.class);
			query.setParameter("username", username);
			query.setParameter("password", password);

			User user = query.getSingleResult();
			if (user == null) {
				throw new Exception("Current password or username are incorrect");
			}
			user.setPassword(newPassword);

			eManager.merge(user);

			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			eManager.close();
		}
	}

	public boolean checkUser(String username, String password) throws Exception {
		EntityManager eManager = JpaUtils.getEntityManager();

		String jpql = "select u from User u where u.username = :username and u.password = :password";

		try {
			TypedQuery<User> query = eManager.createQuery(jpql, User.class);
			query.setParameter("username", username);
			query.setParameter("password", password);

			User user = null;
			try {
				user = query.getSingleResult();
			} catch (Exception e) {
				// Don't take orders Exception
			}
			if (user == null) {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			eManager.close();
		}
		return true;
	}

	public User findByUsernameAndEmail(String username, String email) {
		EntityManager eManager = JpaUtils.getEntityManager();

		String jpql = "select u from User u where u.username = :username and u.email = :email";

		try {
			TypedQuery<User> query = eManager.createQuery(jpql, User.class);
			query.setParameter("username", username);
			query.setParameter("email", email);

			User user = null;
			try {
				user = query.getSingleResult();
			} catch (Exception e) {
				// Don't take orders Exception
			}

			if (user == null) {
				return null;
			}

			return user;
		} finally {
			eManager.close();
		}
	}

	public User findByEmail(String email) {
		EntityManager eManager = JpaUtils.getEntityManager();

		String jpql = "select u from User u where u.email = :email";

		try {
			TypedQuery<User> query = eManager.createQuery(jpql, User.class);
			query.setParameter("email", email);

			return query.getSingleResult();
		} finally {
			eManager.close();
		}
	}
}
