<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="col-4 offset-4 mt-4 mb-4">

	<jsp:include page="/common/inform.jsp"></jsp:include>

	<form action="ForgotPasswordPage" method="post">
		<div class="card">
			<div class="card-header">
				<h3>Forgot password</h3>
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
						id="username" placeholder="User name ?" value="${username }">
				</div>

				<label for="basic-url">Email</label>
				<div class="input-group mb-3">
					<div class="input-group-prepend">
						<div class="input-group-text">
							<i class="fas fa-envelope"></i>
						</div>
					</div>
					<input type="text" class="form-control" name="email" id="email"
						placeholder="Email ?" value="${email }">
				</div>
			</div>
			<div class="card-footer text-muted">
				<button class="btn btn-success"><i class="fas fa-passport"></i> Forgot Password</button>
			</div>
		</div>
	</form>

</div>