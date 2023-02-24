<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>${page.title }</title>
<link rel="icon" type="image/x-icon"
	href="images/icons8-cute-ftp-50.ico">
<base href="/Poly.Asg/">
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>

<body>
	<main class="container">
		<header class="row pt-5 pb-2">
			<div class="col-9">
				<h1>Online Entertaiment</h1>
			</div>

			<div class="col-3 text-right">
				<img src="images/fptlogo.png" alt="" class="mr-4" width="200px"
					height="80px">
			</div>
		</header>

		<nav class="row">
			<nav class="navbar navbar-expand-lg navbar-light bg-light col">
				<a class="navbar-brand" href="SiteHomePage"><img
					src="images/icons8-cute-ftp-50.png" width="30" height="30"
					class="d-inline-block align-top" alt=""></a>
				<button class="navbar-toggler d-lg-none" type="button"
					data-toggle="collapse" data-target="#collapsibleNavId"
					aria-controls="collapsibleNavId" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="collapsibleNavId">
					<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
						<li class="nav-item active"><a class="nav-link"
							href="SiteHomePage"> <i class="fa fa-home" aria-hidden="true"></i>Home
								<span class="sr-only">(current)</span>
						</a></li>
						<li class="nav-item"><a class="nav-link" href="SiteAboutPage"><i
								class="fa fa-info" aria-hidden="true"></i> About Us</a></li>
						<li class="nav-item"><a class="nav-link"
							href="SiteContactPage"><i class="fa fa-id-card"
								aria-hidden="true"></i> Contact Us</a></li>
						<li class="nav-item"><a class="nav-link" href="SiteMyFavoritePage"><i
								class="fa fa-comment" aria-hidden="true"></i> My Favorites</a></li>
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="dropdownId"
							data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								<i class="fa fa-user" aria-hidden="true"></i> <c:if
									test="${not empty siteFullname}">${siteFullname }</c:if> <c:if
									test="${empty siteFullname}">My Account</c:if>
						</a>
							<div class="dropdown-menu" aria-labelledby="dropdownId">
								<c:if test="${siteIsLogin }">
									<a class="dropdown-item" href="SiteLogout">Logoff</a>
									<a class="dropdown-item" href="SiteChangePasswordPage">Change
										Password</a>
									<a class="dropdown-item" href="SiteChangeInformationPage">Edit
										Profile</a>
								</c:if>
								<c:if test="${! siteIsLogin }">
									<a class="dropdown-item" href="SiteLoginPage">Login</a>
									<a class="dropdown-item" href="SiteForgotPassword">Forgot
										Password</a>
									<a class="dropdown-item" href="SiteRegistrationPage">Registration</a>
								</c:if>
							</div></li>
					</ul>
					<form class="form-inline my-2 my-lg-0">
						<input class="form-control mr-sm-2" type="text"
							placeholder="Search">
						<button class="btn btn-outline-success my-2 my-sm-0" type="submit">
							<i class="fa fa-search" aria-hidden="true"></i> Search
						</button>
					</form>
				</div>
			</nav>

		</nav>

		<section class="row">

			<jsp:include page="${page.contenUrl }"></jsp:include>

		</section>

		<footer class="row">
			<div class="col text-center border-top">
				<strong>Fpt Poly &copy;2022. All rights reserved</strong>
			</div>
		</footer>
	</main>

	<c:if test="${not empty page.scriptUrl }">
		<jsp:include page="${page.scriptUrl }"></jsp:include>
	</c:if>

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
</body>

</html>