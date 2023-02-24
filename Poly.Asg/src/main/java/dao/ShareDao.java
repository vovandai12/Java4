package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import dto.ShareUser;
import model.Share;
import utils.JpaUtils;

public class ShareDao extends AbstractEntityDao<Share> {

	public ShareDao() {
		super(Share.class);
	}

	public List<ShareUser> findShareUser() {
		EntityManager eManager = JpaUtils.getEntityManager();
		try {
			String jpql = "select new dto.ShareUser(s.user.username, s.user.fullname, s.video.videoId, s.video.title,s.emails, s.shareDate) from Share s";
			TypedQuery<ShareUser> query = eManager.createQuery(jpql, ShareUser.class);

			return query.getResultList();
		} finally {
			eManager.close();
		}
	}
}
