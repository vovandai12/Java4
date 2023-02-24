
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="col-9 mt-3 mb-3">

	<jsp:include page="/common/inform.jsp"></jsp:include>

	<div class="row">
		<div class="card-columns">
			<c:forEach var="item" items="${videos }">

				<div class="card shadow-sm">
					<img src="${item.poster !=null? item.poster: 'images/No_Image_Available.jpg' }" alt=""
						class="card-img-top">
					<div class="card-img-overlay">
						<h5 class="card-title">
							<a href="SiteDetailPage?videoId=${item.videoId }">${item.title }</a>
						</h5>
						<p class="card-text">
							<small class="text-muted d-inline-block text-truncate" style="max-width: 250px;">${item.description }</small>
						</p>
						<div class="row">
							<c:if test="${siteIsLogin }">
								<a class="btn btn-outline-success text-white m-4"
									data-id="${item.videoId }" data-name="${item.title }"
									onclick="showConfirmLike(this.getAttribute('data-id'), this.getAttribute('data-name'))">
									<i class="fa fa-thumbs-up" aria-hidden="true"></i> Like
								</a>
								<a class="btn btn-outline-info text-white m-4" data-id="${item.videoId }"
									data-name="${item.title }"
									onclick="showConfirmShare(this.getAttribute('data-id'), this.getAttribute('data-name'))">
									<i class="fa fa-share-alt" aria-hidden="true"></i> Share
								</a>
							</c:if>
						</div>
					</div>
					<c:if test="${! siteIsLogin }">
						<div class="card-footer">
							<button type="button" class="btn btn-success" disabled>
								<i class="fa fa-thumbs-up" aria-hidden="true"></i> Like
							</button>
							<button type="button" class="btn btn-info" disabled>
								<i class="fa fa-share-alt" aria-hidden="true"></i> Share
							</button>
						</div>
					</c:if>
				</div>

			</c:forEach>
		</div>
	</div>

	<script>
		function showConfirmLike(id, name) {
			$('#usernameLike').text(name);
			$('#yesOptionLike').attr('href', 'SiteHomePage/like?videoId=' + id);
			$('#confirmationLike').modal('show');
		}

		function showConfirmShare(id, name) {
			$('#usernameShare').text(name);

			document.getElementById("videoId").value = id;
			$('#confirmationShare').modal('show');
		}
	</script>

	<div id="confirmationLike" class="modal fade" tabindex="-1"
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
					Do you want to like ? "<span id="usernameLike"></span>" ?
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">
						<i class="fas fa-times-circle"></i> Close
					</button>
					<a id="yesOptionLike" type="button" class="btn btn-danger"><i
						class="fas fa-check-circle"></i> Submit</a>
				</div>
			</div>
		</div>
	</div>

	<div id="confirmationShare" class="modal fade" tabindex="-1"
		role="dialog" aria-labelledby="modelTitleId" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">
						Send video to your friends ? "<span id="usernameShare"></span>" ?
					</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<form action="SiteHomePage/share" method="post">
					<input type="hidden" name="videoId" id="videoId">
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

	<div class="row mt-3">
		<div class="col">
			<nav aria-label="Page navigation">
				<ul class="pagination justify-content-center">
					<li class="page-item"><a class="page-link"
						href="SiteHomePage/page?page=1" aria-label="Previous"> <span
							aria-hidden="true">&laquo;</span> <span class="sr-only">Previous</span>
					</a></li>

					<c:forEach var="i" begin="1" end="${endPage}">
						<li class="page-item"><a class="page-link"
							href="SiteHomePage/page?page=${i }">${i }</a></li>
					</c:forEach>

					<li class="page-item"><a class="page-link"
						href="SiteHomePage/page?page=${endPage}" aria-label="Next"> <span
							aria-hidden="true">&raquo;</span> <span class="sr-only">Next</span>
					</a></li>
				</ul>
			</nav>
		</div>
	</div>
</div>

<div class="col-3">
	<div class="row mt-3 mb-3">
		<div class="col">
			<div class="card">
				<div class="card-header">
					<i class="fa fa-thumbs-up" aria-hidden="true"></i> Favorites
				</div>
				<div class="list-group list-group-flush">
					<c:forEach var="item" items="${listFavorite }">
						<a href="SiteDetailPage?videoId=${item.videoId }"
							class="list-group-item list-group-item-action">${item.title }</a>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>

	<div class="row mt-3 mb-3">
		<div class="col">
			<div class="card">
				<div class="card-header">
					<i class="fa fa-share-alt" aria-hidden="true"></i> Shares
				</div>
				<div class="list-group list-group-flush">
					<c:forEach var="item" items="${listShare }">
						<a href="SiteDetailPage?videoId=${item.videoId }"
							class="list-group-item list-group-item-action">${item.title }</a>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>

</div>