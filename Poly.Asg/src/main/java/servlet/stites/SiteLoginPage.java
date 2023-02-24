package servlet.stites;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import common.PageInfo;
import common.PageType;
import common.Validation;
import dao.UserDao;
import dto.Login;
import model.User;
import utils.CookieUtils;
import utils.SessionUtils;

@WebServlet("/SiteLoginPage")
public class SiteLoginPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SiteLoginPage() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = CookieUtils.get("siteUsername", request);
		if (username == null) {
			PageInfo.prepareAndForwardSite(request, response, PageType.SITE_LOGIN_PAGE);
			return;
		}
		request.getRequestDispatcher("/SiteHomePage").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Login login = new Login();
			BeanUtils.populate(login, request.getParameterMap());

			StringBuilder sb = new StringBuilder();
			Validation.validateEmpty(login.getPassword(), sb, "<br>Password cannot be left blank !");
			Validation.validateEmpty(login.getUsername(), sb, "<br>Username cannot be left blank !");
			Validation.validateMinLenght(login.getUsername(), 5, sb,
					"<br>User name must be at least 5 characters long !");
			Validation.validateMinLenght(login.getPassword(), 5, sb,
					"<br>Pass word must be at least 5 characters long !");

			if (sb.length() > 0) {

				request.setAttribute("login", login);
				request.setAttribute("error", "Error: input error form" + sb);
				PageInfo.prepareAndForwardSite(request, response, PageType.SITE_LOGIN_PAGE);
				return;
			} else {

				UserDao dao = new UserDao();
				User user = dao.findById(login.getUsername());

				if (dao.checkUser(login.getUsername(), login.getPassword())) {

					SessionUtils.add(request, "siteUsername", login.getUsername());
					SessionUtils.add(request, "siteFullname", user.getFullname());
					if (login.isRemember()) {

						CookieUtils.add("siteUsername", login.getUsername(), 24, response);
						CookieUtils.add("siteFullname", user.getFullname(), 24, response);
					} else {

						CookieUtils.add("siteUsername", login.getUsername(), 0, response);
						CookieUtils.add("siteFullname", user.getFullname(), 0, response);
					}

					request.setAttribute("siteIsLogin", true);
					request.setAttribute("siteUsername", login.getUsername());
					request.setAttribute("siteFullname", user.getFullname());

					request.setAttribute("message", "You have successfully logged into the website!");
					request.getRequestDispatcher("/SiteHomePage").forward(request, response);
					return;
				}
			}

			request.setAttribute("error", "Error: invalid username or password !");
			PageInfo.prepareAndForwardSite(request, response, PageType.SITE_LOGIN_PAGE);
		} catch (Exception e) {

			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
			PageInfo.prepareAndForwardSite(request, response, PageType.SITE_LOGIN_PAGE);
		}
	}

}