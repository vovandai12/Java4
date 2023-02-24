package servlet.admin;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import common.ExportUserExcel;
import common.ExportUserPDF;
import common.PageInfo;
import common.PageType;
import common.Validation;
import dao.UserDao;
import model.User;
import utils.CookieUtils;

@WebServlet({ "/UserPage", "/UserPage/new", "/UserPage/insert", "/UserPage/update", "/UserPage/delete",
		"/UserPage/list", "/UserPage/edit", "/UserPage/page", "/UserPage/excel", "/UserPage/PDF" })
public class UserPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserPage() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String urlString = request.getServletPath();
		switch (urlString) {
		case "/UserPage/new":
			reset(request, response);
			break;
		case "/UserPage/delete":
			delete(request, response);
			break;
		case "/UserPage/list":
			reset(request, response);
			break;
		case "/UserPage/edit":
			edit(request, response);
			break;
		case "/UserPage/page":
			pagination(request, response);
			break;
		case "/UserPage":
			pagination(request, response);
			break;
		case "/UserPage/excel":
			excel(request, response);
			break;
		case "/UserPage/PDF":
			PDF(request, response);
			break;
		default:
			pagination(request, response);
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String urlString = request.getServletPath();
		switch (urlString) {
		case "/UserPage/insert":
			insert(request, response);
			break;
		case "/UserPage/update":
			update(request, response);
			break;
		case "/UserPage/delete":
			delete(request, response);
			break;
		default:
			pagination(request, response);
			break;
		}
	}

	private void reset(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		request.setAttribute("user", user);
		PageInfo.prepareAndForward(request, response, PageType.USER_FORM_MANAGEMRNT_PAGE);
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String usernameLogin = CookieUtils.get("username", request);

		if (username == null) {

			request.setAttribute("error", "Error: Username is required !");
		}

		if (username.equals(usernameLogin)) {
			request.setAttribute("error", "Error: Can not delete logged in user !");
		} else {
			try {

				UserDao dao = new UserDao();
				dao.delete(username);

				request.setAttribute("message", "User is deleted !");
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("error", "Error: " + e.getMessage());
			}
		}

		pagination(request, response);
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			User user = new User();
			BeanUtils.populate(user, request.getParameterMap());

			StringBuilder sb = new StringBuilder();
			Validation.validateEmpty(user.getUsername(), sb, "<br>Username cannot be left blank !");
			Validation.validateEmpty(user.getFullname(), sb, "<br>Fullname cannot be left blank !");
			Validation.validateEmpty(user.getEmail(), sb, "<br>Email cannot be left blank !");
			Validation.validateMinLenght(user.getUsername(), 5, sb,
					"<br>User name must be at least 5 characters long !");
			Validation.validateMinLenght(user.getFullname(), 10, sb,
					"<br>Full name must be at least 10 characters long !");
			Validation.validateEmail(user.getEmail(), sb, "<br>Email invalidate !");

			if (sb.length() > 0) {

				request.setAttribute("user", user);
				request.setAttribute("error", "Error: input error form" + sb);
				PageInfo.prepareAndForward(request, response, PageType.USER_FORM_MANAGEMRNT_PAGE);
				return;
			} else {
				UserDao dao = new UserDao();
				User oUser = dao.findById(user.getUsername());

				if (oUser == null) {
					request.setAttribute("user", user);
					request.setAttribute("error", "Error: the user does not exist, please press the create button !");
					PageInfo.prepareAndForward(request, response, PageType.USER_FORM_MANAGEMRNT_PAGE);
					return;
				}

				user.setPassword(oUser.getPassword());

				dao.update(user);
				request.setAttribute("message", "User is updated !");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
			PageInfo.prepareAndForward(request, response, PageType.USER_FORM_MANAGEMRNT_PAGE);
		}

		pagination(request, response);
	}

	private void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			User user = new User();
			BeanUtils.populate(user, request.getParameterMap());

			user.setPassword("123456789");

			StringBuilder sb = new StringBuilder();
			Validation.validateEmpty(user.getUsername(), sb, "<br>Username cannot be left blank !");
			Validation.validateEmpty(user.getFullname(), sb, "<br>Fullname cannot be left blank !");
			Validation.validateEmpty(user.getEmail(), sb, "<br>Email cannot be left blank !");
			Validation.validateMinLenght(user.getUsername(), 5, sb,
					"<br>User name must be at least 5 characters long !");
			Validation.validateMinLenght(user.getFullname(), 10, sb,
					"<br>Full name must be at least 10 characters long !");
			Validation.validateEmail(user.getEmail(), sb, "<br>Email invalidate !");

			if (sb.length() > 0) {
				request.setAttribute("user", user);
				request.setAttribute("error", "Error: input error form" + sb);
				PageInfo.prepareAndForward(request, response, PageType.USER_FORM_MANAGEMRNT_PAGE);
				return;
			} else {
				UserDao dao = new UserDao();

				if (dao.findById(user.getUsername()) != null) {
					request.setAttribute("user", user);
					request.setAttribute("error", "The user already exists please press the update button !");
					PageInfo.prepareAndForward(request, response, PageType.USER_FORM_MANAGEMRNT_PAGE);
					return;
				}

				dao.insert(user);
				request.setAttribute("message", "User is inserted !");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
			PageInfo.prepareAndForward(request, response, PageType.USER_FORM_MANAGEMRNT_PAGE);
		}

		pagination(request, response);
	}

	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");

		if (username == null) {

			request.setAttribute("error", "Error: Username is required !");
			pagination(request, response);
		}

		try {

			UserDao dao = new UserDao();
			User user = dao.findById(username);

			request.setAttribute("user", user);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
		}

		PageInfo.prepareAndForward(request, response, PageType.USER_FORM_MANAGEMRNT_PAGE);
	}

	private void pagination(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long count;
		int endPage;
		int pageSize = 5;
		int pageNumber;

		if (request.getParameter("page") != null) {
			pageNumber = Integer.parseInt(request.getParameter("page")) - 1;
		} else {
			pageNumber = 0;
		}

		try {
			UserDao dao = new UserDao();

			count = dao.count();
			endPage = (int) (count / pageSize) + 1;
			int firstResult = pageSize * pageNumber;
			List<User> users = dao.findAll(false, firstResult, pageSize);

			request.setAttribute("count", count);
			request.setAttribute("endPage", endPage);
			request.setAttribute("users", users);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
		}

		PageInfo.prepareAndForward(request, response, PageType.USER_MANAGEMRNT_PAGE);
	}

	private void excel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		UserDao dao = new UserDao();
		List<User> list = dao.findAll();
		ExportUserExcel export = new ExportUserExcel(list);
		export.export(response);

		request.setAttribute("message",
				"Exported excel file successfully, check the file in the mail you downloaded !");
		pagination(request, response);
	}

	private void PDF(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
		response.setHeader(headerKey, headerValue);

		UserDao dao = new UserDao();
		List<User> list = dao.findAll();
		ExportUserPDF export = new ExportUserPDF(list);
		export.writeHeaderLine(response);

		request.setAttribute("message", "Exported PDF file successfully, check the file in the mail you downloaded !");
		pagination(request, response);
	}
}
