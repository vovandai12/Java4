package servlet.admin;

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

@WebServlet("/LoginPage")
public class LoginPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginPage() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = CookieUtils.get("username", request);
		String admin = CookieUtils.get("admin", request);
		if (username == null || admin.equals("false")) {
			PageInfo.prepareAndForward(request, response, PageType.ACCOUNT_LOGIN_PAGE);
			return;
		}
		request.getRequestDispatcher("/HomePage").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Login login = new Login();
			BeanUtils.populate(login, request.getParameterMap());

			StringBuilder sb = new StringBuilder();
			Validation.validateEmpty(login.getUsername(), sb, "<br>Username cannot be left blank !");
			Validation.validateEmpty(login.getPassword(), sb, "<br>Password cannot be left blank !");
			Validation.validateMinLenght(login.getUsername(), 5, sb,
					"<br>User name must be at least 5 characters long !");
			Validation.validateMinLenght(login.getPassword(), 5, sb,
					"<br>Pass word must be at least 5 characters long !");

			if (sb.length() > 0) {

				request.setAttribute("error", "Error: input error form" + sb);
				PageInfo.prepareAndForward(request, response, PageType.ACCOUNT_LOGIN_PAGE);
			} else {

				UserDao dao = new UserDao();
				User user = dao.findById(login.getUsername());

				if (dao.checkUser(login.getUsername(), login.getPassword())) {

					if (user.getAdmin()) {

						SessionUtils.add(request, "username", login.getUsername());
						SessionUtils.add(request, "admin", user.getAdmin());
						SessionUtils.add(request, "fullname", user.getFullname());

						if (login.isRemember()) {

							CookieUtils.add("username", login.getUsername(), 24, response);
							CookieUtils.add("admin", String.valueOf(user.getAdmin()), 24, response);
							CookieUtils.add("fullname", user.getFullname(), 24, response);

						} else {

							CookieUtils.add("username", login.getUsername(), 0, response);
							CookieUtils.add("admin", String.valueOf(user.getAdmin()), 0, response);
							CookieUtils.add("fullname", user.getFullname(), 0, response);

						}

						request.setAttribute("isLogin", true);
						request.setAttribute("username", login.getUsername());
						request.setAttribute("admin", String.valueOf(user.getAdmin()));
						request.setAttribute("fullname", user.getFullname());

						request.setAttribute("message", "You have successfully logged into the admin page!");
						request.getRequestDispatcher("/HomePage").forward(request, response);
						return;
					}

					request.setAttribute("error", "Error: new admin account has access !");
					PageInfo.prepareAndForward(request, response, PageType.ACCOUNT_LOGIN_PAGE);
					return;
				}

				request.setAttribute("error", "Error: invalid username or password !");
				PageInfo.prepareAndForward(request, response, PageType.ACCOUNT_LOGIN_PAGE);
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
			PageInfo.prepareAndForward(request, response, PageType.ACCOUNT_LOGIN_PAGE);
		}
	}

}
