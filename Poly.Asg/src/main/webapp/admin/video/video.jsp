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
								<a class="dropdown-item" href="VideoPage/excel">Excel</a> <a
									class="dropdown-item" href="VideoPage/PDF">PDF</a>
							</div>
						</div>
					</div>
					<div class="col-4 d-flex justify-content-center">
						<h3>Video list</h3>
					</div>
					<div class="col-4 d-flex justify-content-end">
						<h3>
							<a href="VideoPage/new"><button type="button"
									class="btn btn-primary">
									<i class="fas fa-plus"></i> New video
								</button></a>
						</h3>
					</div>
				</div>
			</div>
			<div class="card-body">
				<table class="table table-stripe">
					<tr>
						<td>Video ID</td>
						<td>Video Title</td>
						<td>Poster</td>
						<td>View Count</td>
						<td>Status</td>
						<td>&nbsp;</td>
					</tr>
					<c:forEach var="item" items="${videos }">
						<tr>
							<td>${item.videoId }</td>
							<td>${item.title }</td>
							<td><img
								src="${item.poster !=null? item.poster: 'images/No_Image_Available.jpg' }"
								alt="" class="img-product" style="max-width: 150px"></td>
							<td>${item.views }</td>
							<td>${item.active? 'Active':'Inactive' }</td>
							<td><a href="VideoPage/edit?videoId=${item.videoId }"> <i
									class="fa fa-edit" aria-hidden="true"></i> Edit
							</a> <a type="button" class="text-danger" data-id="${item.videoId }"
								data-name="${item.title }"
								onclick="showConfirm(this.getAttribute('data-id'), this.getAttribute('data-name'))">
									<i class="fa fa-trash" aria-hidden="true"></i>Delete
							</a></td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div class="card-footer text-muted">
				<nav aria-label="Page navigation example">
					<ul class="pagination justify-content-center">
						<li class="page-item"><a class="page-link"
							href="VideoPage/page?page=1">Previous</a></li>
						<c:forEach var="i" begin="1" end="${endPage}">
							<li class="page-item"><a class="page-link"
								href="VideoPage/page?page=${i }">${i }</a></li>
						</c:forEach>
						<li class="page-item"><a class="page-link"
							href="VideoPage/page?page=${endPage}">Next</a></li>
					</ul>
				</nav>
			</div>
		</div>

		<script>
			function showConfirm(id, name) {
				$('#username').text(name);
				$('#yesOption').attr('href', 'VideoPage/delete?videoId=' + id);
				$('#confirmationId').modal('show');
			}
		</script>

		<div id="confirmationId" class="modal fade" tabindex="-1"
			role="dialog" aria-labelledby="modelTitleId" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">
							<i class="fas fa-exclamation-circle"></i> Notification
						</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						Do you want to delete ? "<span id="username"></span>" ?
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">
							<i class="fas fa-times-circle"></i> Close
						</button>
						<a id="yesOption" type="button" class="btn btn-danger">
							<i class="fas fa-check-circle"></i> Submit
						</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</c:if>
