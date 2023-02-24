<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:if test="${not empty message }">
	<div class="row">
		<div class="col">
			<div class="alert alert-success alert-dismissible fade show"
				role="alert">
				${message }
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
		</div>
	</div>
</c:if>
<c:if test="${not empty error }">
	<div class="row">
		<div class="col">
			<div class="alert alert-danger alert-dismissible fade show"
				role="alert">
				${error }
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
		</div>
	</div>
</c:if>