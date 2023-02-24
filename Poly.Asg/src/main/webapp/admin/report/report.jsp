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
	<div class="col m-4">

		<jsp:include page="/common/inform.jsp"></jsp:include>

		<ul class="nav nav-tabs mb-3" id="pills-tab" role="tablist">
			<li class="nav-item" role="presentation">
				<button class="nav-link active" id="pills-favorites-tab"
					data-toggle="pill" data-target="#pills-favorites" type="button"
					role="tab" aria-controls="pills-favorites" aria-selected="true">Favorites</button>
			</li>

			<li class="nav-item" role="presentation">
				<button class="nav-link" id="pills-share-tab" data-toggle="pill"
					data-target="#pills-share" type="button" role="tab"
					aria-controls="pills-share" aria-selected="false">Share</button>
			</li>

		</ul>

		<div class="tab-content" id="pills-tabContent">
			<div class="tab-pane fade show active" id="pills-favorites"
				role="tabpanel" aria-labelledby="pills-favorites-tab">
				<div class="card">
					<div class="card-header text-center">
						<div class="row">
							<div class="col-4 d-flex justify-content-start">
								<div class="dropdown">
									<button class="btn btn-secondary dropdown-toggle" type="button"
										data-toggle="dropdown" aria-expanded="false">
										<i class="fas fa-file-export"></i> Export
									</button>
									<div class="dropdown-menu">
										<a class="dropdown-item" href="ReportPage/Favorites/excel">Excel</a> <a
											class="dropdown-item" href="ReportPage/Favorites/PDF">PDF</a>
									</div>
								</div>
							</div>
							<div class="col-4 d-flex justify-content-center">
								<h3>Favorites</h3>
							</div>
							<div class="col-4 d-flex justify-content-end"></div>
						</div>
					</div>
					<div class="card-body">
						<table class="table table-stripe mt-3">
							<tr>
								<td>User name</td>
								<td>Full name</td>
								<td>Video ID</td>
								<td>Video title</td>
								<td>Like date</td>
							</tr>
							<c:forEach var="item" items="${listFavoriteUserAll }">
								<tr>
									<td>${item.username }</td>
									<td>${item.fullname }</td>
									<td>${item.videoId }</td>
									<td>${item.title }</td>
									<td>${item.likeDate }</td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>
			</div>

			<div class="tab-pane fade" id="pills-share" role="tabpanel"
				aria-labelledby="pills-share-tab">
				<div class="card">
					<div class="card-header text-center">
						<div class="row">
							<div class="col-4 d-flex justify-content-start">
								<div class="dropdown">
									<button class="btn btn-secondary dropdown-toggle" type="button"
										data-toggle="dropdown" aria-expanded="false">
										<i class="fas fa-file-export"></i> Export
									</button>
									<div class="dropdown-menu">
										<a class="dropdown-item" href="ReportPage/Shares/excel">Excel</a> <a
											class="dropdown-item" href="ReportPage/Shares/PDF">PDF</a>
									</div>
								</div>
							</div>
							<div class="col-4 d-flex justify-content-center">
								<h3>Share</h3>
							</div>
							<div class="col-4 d-flex justify-content-end"></div>
						</div>
					</div>
					<div class="card-body">
						<table class="table table-stripe mt-3">
							<tr>
								<td>User name</td>
								<td>Full name</td>
								<td>Video ID</td>
								<td>Video title</td>
								<td>Email to</td>
								<td>Share date</td>
							</tr>
							<c:forEach var="item" items="${listShareUserAll }">
								<tr>
									<td>${item.username }</td>
									<td>${item.fullname }</td>
									<td>${item.videoId }</td>
									<td>${item.title }</td>
									<td>${item.emailTo }</td>
									<td>${item.shareDate }</td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>
			</div>
		</div>

	</div>
</c:if>