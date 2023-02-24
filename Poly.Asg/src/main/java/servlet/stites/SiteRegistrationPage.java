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

@WebServlet("/SiteRegistrationPage")
public class SiteRegistrationPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SiteRegistrationPage() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PageInfo.prepareAndForwardSite(request, response, PageType.SITE_REGISTRATION_PAGE);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			User user = new User();
			BeanUtils.populate(user, request.getParameterMap());

			UserDao dao = new UserDao();

			StringBuilder sb = new StringBuilder();
			Validation.validateEmpty(user.getPassword(), sb, "<br>Password cannot be left blank !");
			Validation.validateEmpty(user.getUsername(), sb, "<br>Username cannot be left blank !");
			Validation.validateEmpty(user.getFullname(), sb, "<br>Fullname cannot be left blank !");
			Validation.validateEmpty(user.getEmail(), sb, "<br>Email cannot be left blank !");
			Validation.validateMinLenght(user.getUsername(), 5, sb,
					"<br>User name must be at least 5 characters long !");
			Validation.validateMinLenght(user.getFullname(), 10, sb,
					"<br>Full name must be at least 10 characters long !");
			Validation.validateMinLenght(user.getPassword(), 5, sb,
					"<br>Pass word must be at least 5 characters long !");
			Validation.validateEmail(user.getEmail(), sb, "<br>Email invalidate !");
			if (sb.length() > 0) {
				request.setAttribute("user", user);
				request.setAttribute("error", "Error: input error form" + sb);
				PageInfo.prepareAndForwardSite(request, response, PageType.SITE_REGISTRATION_PAGE);
				return;
			} else {
				if (dao.findByUsernameAndEmail(user.getUsername(), user.getEmail()) != null) {
					request.setAttribute("user", user);
					request.setAttribute("error", "Error: username or email already exists, please choose another username and email!");
					PageInfo.prepareAndForwardSite(request, response, PageType.SITE_REGISTRATION_PAGE);
					return;
				}
				dao.insert(user);
				request.setAttribute("message", "You have successfully registered!");
				request.getRequestDispatcher("/SiteLoginPage").forward(request, response);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
		}
		PageInfo.prepareAndForwardSite(request, response, PageType.SITE_REGISTRATION_PAGE);
	}

}
