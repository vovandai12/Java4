<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="col-8 offset-2 mt-4 mb-4">

	<jsp:include page="/common/inform.jsp"></jsp:include>

	<form action="" method="post">
		<div class="card">
			<div class="card-header">
				<h3>Registration</h3>
			</div>
			<div class="card-body">
				<div class="row">
					<div class="col">
						<label for="basic-url">Username</label>
						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<div class="input-group-text">
									<i class="fas fa-user"></i>
								</div>
							</div>
							<input type="text" class="form-control" name="username"
								id="username" placeholder="Username ?" value="${user.username }">
						</div>

						<label for="basic-url">Fullname</label>
						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<div class="input-group-text">
									<i class="fas fa-file-signature"></i>
								</div>
							</div>
							<input type="text" class="form-control" name="fullname"
								id="fullname" placeholder="Fullname ?" value="${user.fullname }">
						</div>
					</div>

					<div class="col">
						<label for="basic-url">Password</label>
						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<div class="input-group-text">
									<i class="fas fa-lock"></i>
								</div>
							</div>
							<input type="password" class="form-control" name="password"
								id="password" placeholder="Password ?" value="${user.password }">
						</div>

						<label for="basic-url">Email</label>
						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<div class="input-group-text">
									<i class="fas fa-envelope"></i>
								</div>
							</div>
							<input type="text" class="form-control" name="email" id="email"
								placeholder="Email ?" value="${user.email }">
						</div>
					</div>
				</div>
			</div>
			<div class="card-footer text-muted">
				<button class="btn btn-success" formaction="SiteRegistrationPage">
					<i class="fas fa-sign-in-alt"></i> Register
				</button>
			</div>
		</div>
	</form>
</div>