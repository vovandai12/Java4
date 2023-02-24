<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:if test="${! isLogin }">
	<div class="col">
		<div class="alert alert-danger m-4" role="alert">
			Login to perform the admin function, <a href="LoginPage"
				class="alert-link">click the link to login</a>, if you forgot your
			password, <a href="ForgotPasswordPage" class="alert-link">click the link to
				retrieve your password</a>
		</div>
	</div>
</c:if>
<c:if test="${isLogin }">
	<div class="col m-4">

		<jsp:include page="/common/inform.jsp"></jsp:include>

		<form action="" method="post">
			<div class="card">
				<div class="card-header text-center">
					<h3>User editing</h3>
				</div>
				<div class="card-body">
					<div class="row">
						<div class="col-6">
							<label for="basic-url">Username</label>
							<div class="input-group mb-3">
								<div class="input-group-prepend">
									<div class="input-group-text">
										<i class="fas fa-user"></i>
									</div>
								</div>
								<input type="text" class="form-control" name="username"
									id="username" placeholder="Username ?"
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
									id="fullname" placeholder="Fullname ?"
									value="${user.fullname }">
							</div>
						</div>
						<div class="col-6">
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

							<div class="form-check form-check-inline mb-3">
								<label for="" class="m-2"> <input type="radio"
									value="true" class="form-check-input" name="admin" id="admin"
									value="${user.admin? 'checked':'' }">Admin
								</label> <label class="radio-inline m-2" for=""><input
									type="radio" value="false" class="form-check-input"
									name="admin" id="admin" value="${!user.admin? 'checked':'' }">User</label>
							</div>
						</div>
					</div>
				</div>
				<div class="card-footer text-muted">
					<button class="btn btn-primary" formaction="UserPage/insert">
						<i class="fas fa-plus"></i> Create
					</button>
					<button class="btn btn-warning" formaction="UserPage/update">
						<i class="fas fa-wrench"></i> Update
					</button>
					<button class="btn btn-info" formaction="UserPage/list">
						<i class="fas fa-shower"></i> List
					</button>
				</div>
			</div>
		</form>
	</div>
</c:if>