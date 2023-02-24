<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="col-8 offset-2 mt-4 mb-4">

	<jsp:include page="/common/inform.jsp"></jsp:include>

	<form action="SiteChangePasswordPage" method="post">
		<div class="card">
			<div class="card-header">
				<h3>Change password</h3>
			</div>
			<div class="card-body">
				<div class="row">
					<div class="col">
						<div class="form-group">
							<label for="username">Username</label> <input type="text"
								class="form-control" name="username" id="username"
								placeholder="Username ?" readonly value="${username }">
						</div>
						
						<div class="form-group">
							<label for="currentPassword">Current password</label> <input
								type="password" class="form-control" name="currentPassword"
								id="currentPassword" placeholder="Current password ?" value="">
						</div>
					</div>

					<div class="col">
						<div class="form-group">
							<label for="confirmPassword">Confirm password</label> <input
								type="password" class="form-control" name="confirmPassword"
								id="confirmPassword" placeholder="Confirm password ?" value="">
						</div>

						<div class="form-group">
							<label for="password">Password</label> <input type="password"
								class="form-control" name="password" id="password"
								placeholder="Password ?" value="">
						</div>
					</div>
				</div>
			</div>
			<div class="card-footer text-muted">
				<button class="btn btn-success">Change Password</button>
			</div>
		</div>
	</form>

</div>