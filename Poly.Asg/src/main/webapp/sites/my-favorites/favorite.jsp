
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:if test="${! siteIsLogin }">
	<div class="col">
		<div class="alert alert-danger m-4" role="alert">
			Login to perform the function, <a href="SiteLoginPage"
				class="alert-link">click the link to login</a>, if you forgot your
			password, <a href="SiteForgotPassword" class="alert-link">click
				the link to retrieve your password</a>
		</div>
	</div>
</c:if>
<c:if test="${siteIsLogin }">
	<div class="col-12 mt-3 mb-3">

		<jsp:include page="/common/inform.jsp"></jsp:include>

		<div class="card-columns">
			<c:forEach var="item" items="${listFavorite }">

				<div class="card shadow-sm">
					<img src="${item.poster !=null? item.poster: 'images/No_Image_Available.jpg' }" alt=""
						class="card-img-top">
					<div class="card-img-overlay">
						<h5 class="card-title">
							<a href="SiteDetailPage?videoId=${item.videoId }">${item.title }</a>
						</h5>
						<p class="card-text">
							<small class="text-muted">${item.description }</small>
						</p>
						<a class="btn btn-outline-danger m-4" data-id="${item.videoId }"
							data-name="${item.title }"
							onclick="showConfirmDisLike(this.getAttribute('data-id'), this.getAttribute('data-name'))">
							<i class="fa fa-thumbs-up" aria-hidden="true"></i> Dislike
						</a>
					</div>
				</div>

			</c:forEach>
		</div>

	</div>
	
	<script>
		function showConfirmDisLike(id, name) {
			$('#usernameDislike').text(name);
			$('#yesOptionDislike').attr('href', 'SiteMyFavoritePage/dislike?videoId=' + id);
			$('#confirmationDislike').modal('show');
		}
	</script>

	<div id="confirmationDislike" class="modal fade" tabindex="-1"
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
					Do you want to remove the like ? "<span id="usernameDislike"></span>" ?
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">
						<i class="fas fa-times-circle"></i> Close
					</button>
					<a id="yesOptionDislike" type="button" class="btn btn-danger"><i
						class="fas fa-check-circle"></i> Submit</a>
				</div>
			</div>
		</div>
	</div>
</c:if>