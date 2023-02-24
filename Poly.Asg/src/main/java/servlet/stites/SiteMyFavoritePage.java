package servlet.stites;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.PageInfo;
import common.PageType;
import dao.FavoriteDao;
import dao.VideoDao;
import model.Video;
import utils.SessionUtils;

@WebServlet({ "/SiteMyFavoritePage", "/SiteMyFavoritePage/dislike" })
public class SiteMyFavoritePage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SiteMyFavoritePage() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String urlString = request.getServletPath();
		switch (urlString) {
		case "/SiteMyFavoritePage/dislike":
			dislike(request, response);
			break;
		default:
			loadAll(request, response);
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void dislike(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String videoId = request.getParameter("videoId");
		String username = SessionUtils.getLoginedUserNameSite(request);

		if (videoId == null) {
			request.setAttribute("error", "Error: Video id is required !");
			loadAll(request, response);
			return;
		}

		if (username == null) {
			request.getRequestDispatcher("/SiteLoginPage").forward(request, response);
			return;
		}

		try {
			FavoriteDao dao = new FavoriteDao();

			dao.deleteFavorite(username, videoId);

			request.setAttribute("message", "The video has been removed from favorites!");
			loadAll(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
			loadAll(request, response);
		}
	}

	private void loadAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		VideoDao dao = new VideoDao();
		String username = SessionUtils.getLoginedUserNameSite(request);
		List<Video> listFavorite = dao.findFavoriteByUsername(username);
		request.setAttribute("listFavorite", listFavorite);
		PageInfo.prepareAndForwardSite(request, response, PageType.SITE_FAVORITE_PAGE);
	}
}
