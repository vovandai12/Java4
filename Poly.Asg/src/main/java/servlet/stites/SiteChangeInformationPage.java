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
import model.User;
import utils.SessionUtils;

@WebServlet("/SiteChangeInformationPage")
public class SiteChangeInformationPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SiteChangeInformationPage() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = SessionUtils.getLoginedUserNameSite(request);
		if (username == null) {
			request.getRequestDispatcher("/SiteLoginPage").forward(request, response);
			return;
		}

		try {
			UserDao dao = new UserDao();
			User user = dao.findById(username);

			request.setAttribute("user", user);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
		}
		PageInfo.prepareAndForwardSite(request, response, PageType.SITE_CHANGE_INFORMATION_PAGE);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			User user = new User();
			BeanUtils.populate(user, request.getParameterMap());

			String username = SessionUtils.getLoginedUserNameSite(request);
			UserDao dao = new UserDao();

			StringBuilder sb = new StringBuilder();
			Validation.validateEmpty(user.getPassword(), sb, "<br>Password cannot be left blank !");
			Validation.validateEmpty(user.getFullname(), sb, "<br>Fullname cannot be left blank !");
			Validation.validateEmpty(user.getEmail(), sb, "<br>Email cannot be left blank !");
			Validation.validateMinLenght(user.getFullname(), 10, sb,
					"<br>Full name must be at least 10 characters long !");
			Validation.validateMinLenght(user.getPassword(), 5, sb,
					"<br>Pass word must be at least 5 characters long !");
			Validation.validateEmail(user.getEmail(), sb, "<br>Email invalidate !");
			if (sb.length() > 0) {

				request.setAttribute("user", user);
				request.setAttribute("error", "Error: input error form" + sb);
				PageInfo.prepareAndForwardSite(request, response, PageType.SITE_CHANGE_INFORMATION_PAGE);

				return;
			} else {

				User oldUser = dao.findById(username);
				user.setAdmin(oldUser.getAdmin());
				dao.update(user);
				request.setAttribute("user", user);
				request.setAttribute("message", "User profile updated!" + sb);
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
		}

		PageInfo.prepareAndForwardSite(request, response, PageType.SITE_CHANGE_INFORMATION_PAGE);
	}

}
