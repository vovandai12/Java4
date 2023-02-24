package utils;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtils {

	public static void add(HttpServletRequest request, String name, Object value) {
		HttpSession session = request.getSession();
		session.setAttribute(name, value);
	}

	public static String get(HttpServletRequest request, String name) {
		HttpSession session = request.getSession();
		if (session.getAttribute(name) != null) {
			return session.getAttribute(name).toString();
		}
		return null;
	}

	public static void invalidate(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("username");
		session.removeAttribute("fullname");
		session.removeAttribute("admin");
		session.invalidate();
	}

	public static boolean isLogin(ServletRequest request) {
		return get((HttpServletRequest) request, "username") != null;
	}

	public static String getLoginedUserName(HttpServletRequest request) {
		String username = get(request, "username");

		return username == null ? null : username.toString();
	}

	public static String getLoginedAdmin(HttpServletRequest request) {
		String admin = get(request, "admin");

		return admin == null ? null : admin.toString();
	}

	public static String getLoginedFullName(HttpServletRequest request) {
		String fullname = get(request, "fullname");

		return fullname == null ? null : fullname.toString();
	}

	public static void invalidateSite(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("siteUsername");
		session.removeAttribute("siteFullname");
		session.invalidate();
	}

	public static boolean isLoginSite(ServletRequest request) {
		return get((HttpServletRequest) request, "siteUsername") != null;
	}

	public static String getLoginedUserNameSite(HttpServletRequest request) {
		String username = get(request, "siteUsername");

		return username == null ? null : username.toString();
	}

	public static String getLoginedFullNameSite(HttpServletRequest request) {
		String fullname = get(request, "siteFullname");

		return fullname == null ? null : fullname.toString();
	}
}
