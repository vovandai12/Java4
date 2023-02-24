package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import utils.SessionUtils;

@WebFilter(urlPatterns = "/*")
public class AuthFilter implements Filter {

	public AuthFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		request.setAttribute("isLogin", SessionUtils.isLogin(request));
		request.setAttribute("username", SessionUtils.getLoginedUserName((HttpServletRequest) request));
		request.setAttribute("admin", SessionUtils.getLoginedAdmin((HttpServletRequest) request));
		request.setAttribute("fullname", SessionUtils.getLoginedFullName((HttpServletRequest) request));
		
		request.setAttribute("siteIsLogin", SessionUtils.isLoginSite(request));
		request.setAttribute("siteUsername", SessionUtils.getLoginedUserNameSite((HttpServletRequest) request));
		request.setAttribute("siteFullname", SessionUtils.getLoginedFullNameSite((HttpServletRequest) request));
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}
}
