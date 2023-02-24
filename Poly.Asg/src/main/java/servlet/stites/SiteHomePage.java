package servlet.stites;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.PageInfo;
import common.PageType;
import common.Validation;
import dao.FavoriteDao;
import dao.ShareDao;
import dao.UserDao;
import dao.VideoDao;
import dto.Email;
import model.Favorite;
import model.Share;
import model.User;
import model.Video;
import utils.EmailUtils;
import utils.SessionUtils;

@WebServlet({ "/SiteHomePage", "/SiteHomePage/page", "/SiteHomePage/like", "/SiteHomePage/share" })
public class SiteHomePage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SiteHomePage() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String urlString = request.getServletPath();
		switch (urlString) {
		case "/SiteHomePage/page":
			pagination(request, response);
			break;
		case "/SiteHomePage/like":
			like(request, response);
			break;
		case "/SiteHomePage/share":
			share(request, response);
			break;
		default:
			pagination(request, response);
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void pagination(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		VideoDao dao = new VideoDao();
		Long count;
		int endPage;
		int pageSize = 12;
		int pageNumber;
		if (request.getParameter("page") != null) {
			pageNumber = Integer.parseInt(request.getParameter("page")) - 1;
		} else {
			pageNumber = 0;
		}
		try {
			count = dao.count();
			endPage = (int) (count / pageSize) + 1;
			int firstResult = pageSize * pageNumber;
			List<Video> videos = dao.findAll(false, firstResult, pageSize);
			request.setAttribute("count", count);
			request.setAttribute("endPage", endPage);
			request.setAttribute("videos", videos);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
		}

		String username = SessionUtils.getLoginedUserNameSite(request);

		List<Video> listFavorite = dao.findFavoriteByUsername(username);
		request.setAttribute("listFavorite", listFavorite);

		List<Video> listShare = dao.findShareByUsername(username);
		request.setAttribute("listShare", listShare);

		PageInfo.prepareAndForwardSite(request, response, PageType.SITE_HOME_PAGE);
	}

	private void share(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String videoId = request.getParameter("videoId");
		String username = SessionUtils.getLoginedUserNameSite(request);
		String emailShare = request.getParameter("email");
		String msg = request.getParameter("msg");
		if (videoId == null) {
			request.setAttribute("error", "Error: Video id is required !");
			pagination(request, response);
			return;
		}

		if (username == null) {
			request.getRequestDispatcher("/SiteLoginPage").forward(request, response);
			return;
		}

		StringBuilder sb = new StringBuilder();
		Validation.validateEmail(emailShare, sb, "-Shared email is wrong format !");
		Validation.validateEmpty(emailShare, sb, "-Email share cannot be blank !");
		if (sb.length() > 0) {
			request.setAttribute("error", sb);
			pagination(request, response);
			return;
		}

		try {
			VideoDao daoVideo = new VideoDao();
			Video video = daoVideo.findById(videoId);

			UserDao daoUser = new UserDao();
			User user = daoUser.findById(username);

			Email email = new Email();
			email.setTo(emailShare);
			email.setSubject("Share video");
			email.setContent("Dear Ms/Mr. " + "<br>The video is more interesting and I want to share with you "
					+ "<br>Please click the link <a href='http://localhost:8080/Poly.Asg/SiteDetailPage?videoId="
					+ videoId + "'>view video</a>" + "<br>");
			email.setMsg(msg);
			EmailUtils.send(email);

			ShareDao dao = new ShareDao();
			Share share = new Share();
			share.setUser(user);
			share.setVideo(video);
			share.setEmails(emailShare);
			share.setShareDate(new Date());
			dao.insert(share);

			request.setAttribute("message", "Video has been shared to email");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
		}
		pagination(request, response);
	}

	private void like(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String videoId = request.getParameter("videoId");
		String username = SessionUtils.getLoginedUserNameSite(request);

		if (videoId == null) {
			request.setAttribute("error", "Error: Video id is required !");
			pagination(request, response);
			return;
		}

		if (username == null) {
			request.getRequestDispatcher("/SiteLoginPage").forward(request, response);
			return;
		}

		try {
			FavoriteDao dao = new FavoriteDao();

			if (dao.checkFavorite(username, videoId)) {
				request.setAttribute("message", "You already like this product!");
				pagination(request, response);
				return;
			}

			VideoDao daoVideo = new VideoDao();
			Video video = daoVideo.findById(videoId);

			UserDao daoUser = new UserDao();
			User user = daoUser.findById(username);

			Favorite favorite = new Favorite();
			favorite.setUser(user);
			favorite.setVideo(video);
			favorite.setLikeDate(new Date());

			dao.insert(favorite);

			request.setAttribute("message", "Added video to favorites!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
		}
		pagination(request, response);
	}

}
