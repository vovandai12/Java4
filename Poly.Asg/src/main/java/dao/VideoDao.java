package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Video;
import utils.JpaUtils;

public class VideoDao extends AbstractEntityDao<Video> {

	public VideoDao() {
		super(Video.class);
	}

	public List<Video> findFavoriteByUsername(String username) {
		EntityManager eManager = JpaUtils.getEntityManager();

		String jpql = "select o.video from Favorite o where o.user.username = :username";

		try {
			TypedQuery<Video> query = eManager.createQuery(jpql, Video.class);
			query.setParameter("username", username);

			return query.getResultList();
		} finally {
			eManager.close();
		}
	}

	public List<Video> findShareByUsername(String username) {
		EntityManager eManager = JpaUtils.getEntityManager();

		String jpql = "select o.video from Share o where o.user.username = :username";

		try {
			TypedQuery<Video> query = eManager.createQuery(jpql, Video.class);
			query.setParameter("username", username);

			return query.getResultList();
		} finally {
			eManager.close();
		}
	}

}
