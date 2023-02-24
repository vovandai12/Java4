package common;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;

public class PageInfo {
	public static Map<PageType, PageInfo> pageRote = new HashedMap();

	static {
		pageRote.put(PageType.HOME_MANAGEMRNT_PAGE, new PageInfo("Home Management", "/admin/home/home.jsp", null));
		pageRote.put(PageType.USER_MANAGEMRNT_PAGE, new PageInfo("User Management", "/admin/users/user.jsp", null));
		pageRote.put(PageType.USER_FORM_MANAGEMRNT_PAGE,
				new PageInfo("User Management", "/admin/users/form.jsp", null));
		pageRote.put(PageType.REPORT_MANAGEMRNT_PAGE,
				new PageInfo("Report Management", "/admin/report/report.jsp", null));
		pageRote.put(PageType.VIDEO_MANAGEMRNT_PAGE, new PageInfo("Video Management", "/admin/video/video.jsp", null));
		pageRote.put(PageType.VIDEO_FORM_MANAGEMRNT_PAGE,
				new PageInfo("Video Management", "/admin/video/form.jsp", null));
		pageRote.put(PageType.ACCOUNT_LOGIN_PAGE, new PageInfo("Login Management", "/admin/account/login.jsp", null));
		pageRote.put(PageType.ACCOUNT_CHANGE_PASSWORD_PAGE,
				new PageInfo("Change Password Management", "/admin/account/change-password.jsp", null));
		pageRote.put(PageType.ACCOUNT_CHANGE_INFORMATION_PAGE,
				new PageInfo("Change information Management", "/admin/account/change-Information.jsp", null));
		pageRote.put(PageType.ACCOUNT_FORGOT_PASSWORD_PAGE,
				new PageInfo("Forgot Password Management", "/admin/account/forgot-password.jsp", null));

		pageRote.put(PageType.SITE_HOME_PAGE, new PageInfo("Home Page", "/sites/home/home.jsp", null));
		pageRote.put(PageType.SITE_LOGIN_PAGE, new PageInfo("Login Page", "/sites/account/login.jsp", null));
		pageRote.put(PageType.SITE_REGISTRATION_PAGE,
				new PageInfo("Registration Page", "/sites/account/registration.jsp", null));
		pageRote.put(PageType.SITE_CHANGE_INFORMATION_PAGE,
				new PageInfo("Change information Page", "/sites/account/change-Information.jsp", null));
		pageRote.put(PageType.SITE_FORGOT_PASSWORD_PAGE,
				new PageInfo("Forgot Password Page", "/sites/account/forgot-password.jsp", null));
		pageRote.put(PageType.SITE_CHANGE_PASSWORD_PAGE,
				new PageInfo("Change Password Page", "/sites/account/change-password.jsp", null));
		pageRote.put(PageType.SITE_FAVORITE_PAGE,
				new PageInfo("Video Favorite Page", "/sites/my-favorites/favorite.jsp", null));
		pageRote.put(PageType.SITE_DETAIL_PAGE, new PageInfo("Video Detail Page", "/sites/detail/detail.jsp", null));
		pageRote.put(PageType.SITE_ABOUT_PAGE, new PageInfo("About Us Page", "/sites/about/about-us.jsp", null));
		pageRote.put(PageType.SITE_CONTACT_PAGE,
				new PageInfo("Contact Us Page", "/sites/contact/contact-us.jsp", null));

	}

	public static void prepareAndForward(HttpServletRequest request, HttpServletResponse response, PageType pageType)
			throws ServletException, IOException {
		PageInfo pageInfo = pageRote.get(pageType);

		request.setAttribute("page", pageInfo);

		request.getRequestDispatcher("/admin/layout.jsp").forward(request, response);
	}

	public static void prepareAndForwardSite(HttpServletRequest request, HttpServletResponse response,
			PageType pageType) throws ServletException, IOException {
		PageInfo pageInfo = pageRote.get(pageType);

		request.setAttribute("page", pageInfo);

		request.getRequestDispatcher("/sites/layout.jsp").forward(request, response);
	}

	private String title;
	private String contenUrl;
	private String scriptUrl;

	public PageInfo(String title, String contenUrl, String scriptUrl) {
		super();
		this.title = title;
		this.contenUrl = contenUrl;
		this.scriptUrl = scriptUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContenUrl() {
		return contenUrl;
	}

	public void setContenUrl(String contenUrl) {
		this.contenUrl = contenUrl;
	}

	public String getScriptUrl() {
		return scriptUrl;
	}

	public void setScriptUrl(String scriptUrl) {
		this.scriptUrl = scriptUrl;
	}
}
