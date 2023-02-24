package servlet.stites;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.CookieUtils;
import utils.SessionUtils;

@WebServlet("/SiteLogout")
public class SiteLogout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SiteLogout() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CookieUtils.add("siteUsername", null, 0, response);
		CookieUtils.add("siteFullname", null, 0, response);

		SessionUtils.invalidate(request);

		request.setAttribute("siteIsLogin", false);
		request.setAttribute("siteUsername", null);
		request.setAttribute("siteFullname", null);

		request.setAttribute("message", "You have successfully logged out, thank you for using our website!");
		request.getRequestDispatcher("/SiteHomePage").forward(request, response);
	}

}
