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
import dto.ChangePassword;
import utils.SessionUtils;

@WebServlet("/SiteChangePasswordPage")
public class SiteChangePasswordPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SiteChangePasswordPage() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = SessionUtils.getLoginedUserNameSite(request);

		if (username == null) {

			request.getRequestDispatcher("/SiteLoginPage").forward(request, response);
			return;
		}

		request.setAttribute("username", username);
		PageInfo.prepareAndForwardSite(request, response, PageType.SITE_CHANGE_PASSWORD_PAGE);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String username = SessionUtils.getLoginedUserNameSite(request);

			ChangePassword changePassword = new ChangePassword();
			BeanUtils.populate(changePassword, request.getParameterMap());
			request.setAttribute("username", username);

			StringBuilder sb = new StringBuilder();
			Validation.validateEmpty(changePassword.getPassword(), sb, "<br>Pass word cannot be left blank !");
			Validation.validateEmpty(changePassword.getConfirmPassword(), sb,
					"<br>Pass confirm cannot be left blank !");
			Validation.validateEmpty(changePassword.getCurrentPassword(), sb,
					"<br>Pass current cannot be left blank !");
			Validation.validateMinLenght(changePassword.getPassword(), 5, sb,
					"<br>Pass word must be at least 5 characters long !");
			Validation.validateMinLenght(changePassword.getConfirmPassword(), 5, sb,
					"<br>Pass confirm word must be at least 5 characters long !");
			Validation.validateMinLenght(changePassword.getCurrentPassword(), 5, sb,
					"<br>Pass current word must be at least 5 characters long !");

			if (sb.length() > 0) {
				request.setAttribute("username", username);
				request.setAttribute("error", "Error: input error form" + sb);
				PageInfo.prepareAndForwardSite(request, response, PageType.SITE_CHANGE_PASSWORD_PAGE);

				return;
			}

			if (!changePassword.getConfirmPassword().equals(changePassword.getPassword())) {
				request.setAttribute("error", "New password and new confim password are not identical !");
				PageInfo.prepareAndForwardSite(request, response, PageType.SITE_CHANGE_PASSWORD_PAGE);

				return;
			}

			UserDao dao = new UserDao();

			if (!dao.checkUser(changePassword.getUsername(), changePassword.getCurrentPassword())) {
				request.setAttribute("error", "The current password does not match the account !");
				PageInfo.prepareAndForwardSite(request, response, PageType.SITE_CHANGE_PASSWORD_PAGE);

				return;
			}

			dao.changePassword(changePassword.getUsername(), changePassword.getCurrentPassword(),
					changePassword.getPassword());
			request.setAttribute("message", "Password has bean changed. Please log in again !");
			request.getRequestDispatcher("/SiteLogout").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
			PageInfo.prepareAndForwardSite(request, response, PageType.SITE_CHANGE_PASSWORD_PAGE);
		}
	}

}
