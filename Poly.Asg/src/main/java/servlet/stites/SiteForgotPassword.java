package servlet.stites;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.PageInfo;
import common.PageType;
import common.Validation;
import dao.UserDao;
import dto.Email;
import model.User;
import utils.EmailUtils;

@WebServlet("/SiteForgotPassword")
public class SiteForgotPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SiteForgotPassword() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PageInfo.prepareAndForwardSite(request, response, PageType.SITE_FORGOT_PASSWORD_PAGE);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String username = request.getParameter("username");
			String email = request.getParameter("email");

			StringBuilder sb = new StringBuilder();
			Validation.validateEmpty(username, sb, "<br>Username cannot be left blank !");
			Validation.validateEmpty(email, sb, "<br>Email cannot be left blank !");
			Validation.validateMinLenght(username, 5, sb, "<br>User name must be at least 5 characters long !");
			Validation.validateEmail(email, sb, "<br>Email invalidate !");

			if (sb.length() > 0) {
				request.setAttribute("error", "Error: input error form" + sb);
				PageInfo.prepareAndForwardSite(request, response, PageType.SITE_FORGOT_PASSWORD_PAGE);

				return;
			}

			UserDao dao = new UserDao();
			User user = dao.findByUsernameAndEmail(username, email);

			if (user == null) {
				request.setAttribute("error", "Error: username or email does not exist!");
				PageInfo.prepareAndForwardSite(request, response, PageType.SITE_FORGOT_PASSWORD_PAGE);

				return;
			}

			Email sendEmail = new Email();
			sendEmail.setTo(email);
			sendEmail.setSubject("Forgot password");
			sendEmail.setContent("Dear Ms/Mr. " + "<br>A password reset request has been sent "
					+ "<br>If you are, this is your access password: " + user.getPassword() + "<br>");
			EmailUtils.send(sendEmail);

			request.setAttribute("message",
					"An email asking for password reset confirmation has been sent to your email");
			request.getRequestDispatcher("/SiteLoginPage").forward(request, response);
			return;
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
		}
		PageInfo.prepareAndForwardSite(request, response, PageType.SITE_FORGOT_PASSWORD_PAGE);
	}

}
