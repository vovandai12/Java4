<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:if test="${! isLogin }">
	<div class="col">
		<div class="alert alert-danger m-4" role="alert">
			Login to perform the admin function, <a href="LoginPage"
				class="alert-link">click the link to login</a>, if you forgot your
			password, <a href="ForgotPasswordPage" class="alert-link">click
				the link to retrieve your password</a>
		</div>
	</div>
</c:if>
<c:if test="${isLogin }">
	<div class="col-8 offset-2 mt-4 mb-4">

		<jsp:include page="/common/inform.jsp"></jsp:include>

		<form action="ChangeInformationPage" method="post">
			<div class="card">
				<div class="card-header">
					<h3>Change information</h3>
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
									id="username" placeholder="User name ?" readonly
									value="${user.username }">
							</div>

							<label for="basic-url">Fullname</label>
							<div class="input-group mb-3">
								<div class="input-group-prepend">
									<div class="input-group-text">
										<i class="fas fa-file-signature"></i>
									</div>
								</div>
								<input type="text" class="form-control" name="fullname"
									id="fullname" placeholder="Full name ?"
									value="${user.fullname }">
							</div>
						</div>

						<div class="col">
							<label for="basic-url">Password</label>
							<div class="input-group mb-3">
								<div class="input-group-prepend">
									<div class="input-group-text">
										<i class="fas fa-unlock-alt"></i>
									</div>
								</div>
								<input type="password" class="form-control" name="password"
									id="password" placeholder="Password ?"
									value="${user.password }">
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
					<button class="btn btn-success">
						<i class="fas fa-wrench"></i> Update
					</button>
				</div>
			</div>
		</form>
	</div>
</c:if>