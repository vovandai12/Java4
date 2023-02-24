<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="col-12 mt-4">

	<jsp:include page="/common/inform.jsp"></jsp:include>

	<div class="p-2 row">
		<div class="col-7">
			<div class="card shadow-sm">
				<img src="${video.poster !=null? video.poster: 'images/No_Image_Available.jpg' }" alt="" class="card-img-top">
				<div class="card-body">
					<h5 class="card-title">${video.title }</h5>
					<p class="card-text">
						<small class="text-muted">${video.description }</small>
					</p>
				</div>
				<div class="card-footer">
					<c:if test="${siteIsLogin }">
						<button type="button" class="btn btn-success" data-toggle="modal"
							data-target="#exampleModalLike">
							<i class="fa fa-thumbs-up" aria-hidden="true"></i> Like
						</button>
						<button type="button" class="btn btn-info" data-toggle="modal"
							data-target="#exampleModalShare">
							<i class="fa fa-share-alt" aria-hidden="true"></i> Share
						</button>
					</c:if>
					<c:if test="${! siteIsLogin }">
						<button type="button" class="btn btn-success" disabled>
							<i class="fa fa-thumbs-up" aria-hidden="true"></i> Like
						</button>
						<button type="button" class="btn btn-info" disabled>
							<i class="fa fa-share-alt" aria-hidden="true"></i> Share
						</button>
					</c:if>
				</div>
			</div>
		</div>

		<div class="modal fade" id="exampleModalLike" tabindex="-1"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">
							<i class="fas fa-exclamation-circle"></i> Notification
						</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">Do you want to like ? " ${video.title }
						" ?</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">
							<i class="fas fa-times-circle"></i> Close
						</button>
						<a type="button" class="btn btn-danger"
							href="SiteDetailPage/like?videoId=${video.videoId }"><i
							class="fas fa-check-circle"></i> Submit</a>
					</div>
				</div>
			</div>
		</div>

		<div class="modal fade" id="exampleModalShare" tabindex="-1"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Send video to
							your friends ? " ${video.title } " ?</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<form action="SiteDetailPage/share" method="post">
						<input type="hidden" name="videoId" id="videoId"
							value="${video.videoId }">
						<div class="modal-body">
							<label for="basic-url">Your Friend email:</label>
							<div class="input-group mb-3">
								<div class="input-group-prepend">
									<div class="input-group-text">
										<i class="fas fa-envelope"></i>
									</div>
								</div>
								<input type="text" class="form-control" name="email" id="email"
									placeholder="Email ?">
							</div>

							<label for="basic-url">Message:</label>
							<div class="input-group mb-3">
								<div class="input-group-prepend">
									<div class="input-group-text">
										<i class="fas fa-comments"></i>
									</div>
								</div>
								<input type="text" class="form-control" name="msg" id="msg"
									placeholder="Message ?">
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">
								<i class="fas fa-times-circle"></i> Close
							</button>
							<button type="submit" class="btn btn-danger">
								<i class="fas fa-check-circle"></i> Send
							</button>
						</div>
					</form>
				</div>
			</div>
		</div>

		<div class="col-5">

			<c:forEach var="item" items="${listVideo }">
				<div class="card mb-3" style="max-width: 540px;">
					<div class="row no-gutters">
						<div class="col-md-4">
							<img src="${item.poster !=null? item.poster: 'images/No_Image_Available.jpg' }" alt="" class="img-fluid">
						</div>
						<div class="col-md-8">
							<div class="card-body">
								<h5 class="card-title"><a href="SiteDetailPage?videoId=${item.videoId }">${item.title }</a></h5>
								<p class="card-text d-inline-block text-truncate" style="max-width: 250px;">${item.description }</p>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>

		</div>

	</div>

</div>
