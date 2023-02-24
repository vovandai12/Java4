package servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.CookieUtils;
import utils.SessionUtils;

@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Logout() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CookieUtils.add("username", null, 0, response);
		CookieUtils.add("admin", null, 0, response);
		CookieUtils.add("fullname", null, 0, response);

		SessionUtils.invalidate(request);

		request.setAttribute("isLogin", null);
		request.setAttribute("username", null);
		request.setAttribute("admin", null);
		request.setAttribute("fullname", null);

		request.getRequestDispatcher("/HomePage").forward(request, response);
	}

}
