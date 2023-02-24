package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import dto.FavoriteUser;
import model.Favorite;
import utils.JpaUtils;

public class FavoriteDao extends AbstractEntityDao<Favorite> {

	public FavoriteDao() {
		super(Favorite.class);
	}

	public List<FavoriteUser> findFavoriteUser() {
		EntityManager eManager = JpaUtils.getEntityManager();
		try {
			String jpql = "select new dto.FavoriteUser(f.user.username, f.user.fullname, f.video.videoId, f.video.title, f.likeDate) from Favorite f";
			TypedQuery<FavoriteUser> query = eManager.createQuery(jpql, FavoriteUser.class);

			return query.getResultList();
		} finally {
			eManager.close();
		}
	}

	public boolean checkFavorite(String username, String videoId) throws Exception {
		EntityManager eManager = JpaUtils.getEntityManager();

		String jpql = "select f from Favorite f where f.user.username = :username and f.video.videoId = :videoId";

		try {
			TypedQuery<Favorite> query = eManager.createQuery(jpql, Favorite.class);
			query.setParameter("username", username);
			query.setParameter("videoId", videoId);

			Favorite favorite = query.getSingleResult();
			if (favorite == null) {
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

	public void deleteFavorite(String username, String videoId) throws Exception {
		EntityManager eManager = JpaUtils.getEntityManager();

		EntityTransaction transaction = eManager.getTransaction();

		String jpql = "select f from Favorite f where f.user.username = :username and f.video.videoId = :videoId";

		try {
			transaction.begin();

			TypedQuery<Favorite> query = eManager.createQuery(jpql, Favorite.class);
			query.setParameter("username", username);
			query.setParameter("videoId", videoId);

			Favorite favorite = query.getSingleResult();
			eManager.remove(favorite);

			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			eManager.close();
		}
	}
}
