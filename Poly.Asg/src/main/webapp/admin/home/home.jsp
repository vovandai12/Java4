<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:if test="${! isLogin }">
	<div class="col">
		<div class="alert alert-danger m-4" role="alert">
			Login to perform the admin function, <a href="LoginPage" class="alert-link">click
				the link to login</a>, if you forgot your password, <a href="ForgotPasswordPage"
				class="alert-link">click the link to retrieve your password</a>
		</div>
	</div>
</c:if>
<c:if test="${isLogin }">
	<h1>Home page</h1>
</c:if>