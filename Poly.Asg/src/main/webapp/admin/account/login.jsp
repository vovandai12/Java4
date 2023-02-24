<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="offset-3 col-6 mt-4 mb-4">

	<jsp:include page="/common/inform.jsp"></jsp:include>

	<form action="LoginPage" method="post">
		<div class="card">
			<div class="card-header">
				<h3>Login admin</h3>
			</div>
			<div class="card-body">
				<label for="basic-url">Username</label>
				<div class="input-group mb-3">
					<div class="input-group-prepend">
						<div class="input-group-text">
							<i class="fas fa-user"></i>
						</div>
					</div>
					<input type="text" class="form-control" name="username"
						id="username" placeholder="Username ?" value="${login.username }">
				</div>

				<label for="basic-url">Password</label>
				<div class="input-group mb-3">
					<div class="input-group-prepend">
						<div class="input-group-text">
							<i class="fas fa-lock"></i>
						</div>
					</div>
					<input type="password" class="form-control" name="password"
						id="password" placeholder="Password ?">
				</div>

				<div class="input-group form-check-inline mb-3">
					<input type="checkbox" class="form-check-input" name="remember">Remember
					me ?
				</div>
			</div>
			<div class="card-footer text-muted">
				<button type="submit" class="btn btn-success">
					<i class="fas fa-sign-in-alt"></i> Login
				</button>
			</div>
		</div>
	</form>
</div>