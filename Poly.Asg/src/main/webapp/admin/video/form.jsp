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

		<form action="" method="post" enctype="multipart/form-data">
			<div class="card">
				<div class="card-header text-center">
					<h3>Video editing</h3>
				</div>
				<div class="card-body">
					<div class="row">
						<div class="col-3">
							<div class="border p-3">
								<img
									src="${video.poster !=null?video.poster: 'images/No_Image_Available.jpg' }"
									alt="" class="img-fluid" id="blah">
								<div class="input-group mb-3 mt-3">
									<div class="input-group-prepend">
										<span class="input-group-text">Upload</span>
									</div>
									<div class="custom-file">
										<input type="file" class="custom-file-input" id="cover"
											name="cover" onchange="readURL(this);"> <label
											for="cover">Choose File</label>
									</div>
								</div>
							</div>
						</div>
						<script>
							function readURL(input) {
								if (input.files && input.files[0]) {
									var reader = new FileReader();

									reader.onload = function(e) {
										$('#blah').attr('src', e.target.result);
									};

									reader.readAsDataURL(input.files[0]);
								}
							}
						</script>
						<div class="col-9">
							<label for="basic-url">Video ID</label>
							<div class="input-group mb-3">
								<div class="input-group-prepend">
									<div class="input-group-text">
										<i class="fas fa-qrcode"></i>
									</div>
								</div>
								<input type="text" class="form-control" name="videoId"
									id="videoId" placeholder="Video Id ?" value="${video.videoId }">
							</div>

							<label for="basic-url">Video Title</label>
							<div class="input-group mb-3">
								<div class="input-group-prepend">
									<div class="input-group-text">
										<i class="fas fa-heading"></i>
									</div>
								</div>
								<input type="text" class="form-control" name="title" id="title"
									placeholder="Video Title ?" value="${video.title }">
							</div>

							<label for="basic-url">View Count</label>
							<div class="input-group mb-3">
								<div class="input-group-prepend">
									<div class="input-group-text">
										<i class="fas fa-eye"></i>
									</div>
								</div>
								<input type="number" class="form-control" name="views"
									id="views" placeholder="View Count ?" min="1"
									value="${video.views }">
							</div>

							<div class="form-check form-check-inline">
								<label for="" class="m-2"> <input type="radio"
									value="true" class="form-check-input" name="active" id="active"
									value="${video.active? 'checked':'' }">Active
								</label> <label class="radio-inline m-2" for=""><input
									type="radio" value="false" class="form-check-input"
									name="active" id="active"
									value="${!video.active? 'checked':'' }">Inactive</label>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col">
							<label for="description">Description</label>
							<textarea name="description" id="description" cols="30" rows="4"
								class="form-control" value="${video.description }"></textarea>
						</div>
					</div>
				</div>
				<div class="card-footer text-muted">
					<button class="btn btn-primary" formaction="VideoPage/insert">
						<i class="fas fa-plus"></i> Create
					</button>
					<button class="btn btn-warning" formaction="VideoPage/update">
						<i class="fas fa-wrench"></i> Update
					</button>
					<button class="btn btn-info" formaction="VideoPage/list">
						<i class="fas fa-shower"></i> List
					</button>
				</div>
			</div>
		</form>
	</div>
</c:if>