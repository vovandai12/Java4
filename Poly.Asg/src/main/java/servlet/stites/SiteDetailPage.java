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

@WebServlet({ "/SiteDetailPage", "/SiteDetailPage/like", "/SiteDetailPage/share" })
public class SiteDetailPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SiteDetailPage() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String urlString = request.getServletPath();
		switch (urlString) {
		case "/SiteDetailPage":
			detail(request, response);
			break;
		case "/SiteDetailPage/like":
			like(request, response);
			break;
		case "/SiteDetailPage/share":
			share(request, response);
			break;
		default:
			detail(request, response);
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String videoId = request.getParameter("videoId");
		if (videoId == null) {
			request.setAttribute("error", "Error: Video id is required !");
			PageInfo.prepareAndForwardSite(request, response, PageType.SITE_DETAIL_PAGE);
			return;
		}
		try {
			VideoDao dao = new VideoDao();
			Video video = dao.findById(videoId);
			video.setViews(video.getViews() + 1);
			dao.update(video);

			request.setAttribute("video", video);

			List<Video> videos = dao.findAll(false, 1, 5);
			request.setAttribute("listVideo", videos);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
		}
		PageInfo.prepareAndForwardSite(request, response, PageType.SITE_DETAIL_PAGE);
	}

	private void share(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String videoId = request.getParameter("videoId");
		String username = SessionUtils.getLoginedUserNameSite(request);
		String emailShare = request.getParameter("email");
		String msg = request.getParameter("msg");
		if (videoId == null) {
			request.setAttribute("error", "Error: Video id is required !");
			detail(request, response);
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
			detail(request, response);
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
		detail(request, response);
	}

	private void like(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String videoId = request.getParameter("videoId");
		String username = SessionUtils.getLoginedUserNameSite(request);
		if (videoId == null) {
			request.setAttribute("error", "Error: Video id is required !");
			detail(request, response);
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
				detail(request, response);
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
		detail(request, response);
	}
}
