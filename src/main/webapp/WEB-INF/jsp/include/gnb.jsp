<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="d-flex justify-content-between align-items-center">
	<div class="logo">
		<div class="logo_font font-weight-bold"><a href="http://localhost/user/sign_in_view">Changstagram</a></div>
	</div>
	<c:if test="${not empty userName }">
		<div class="btn-group d-flex justify-content-center mt-3">
			<input id="searchinput" type="search" class="form-control"
				placeholder="검색"> <span id="searchclear"
				class="glyphicon glyphicon-remove-circle mb-3 d-none"> <img
				alt=""
				src="https://ww.namu.la/s/096ff2aacd1068d19f89d10f45bbf1db309d3f66b5d23c07ef113ab8a1ef72e8e1902b0b49ceb816c0b5cf894e82859704b9b059925448f42ac3c07dfcfafa1f5ec1c2b35dda3bb5579e2dc22e236ff1"
				width="20px" height="25px">
			</span>
		</div>
		<div class="d-flex mt-3 mr-5">
			<h5 class="mr-5">${loginId}</h5>
			<h5 class="font-weight-bold text-white mr-5">
				<a href="/user/user_info">내 정보</a>
			</h5>
			<h5 class="font-weight-bold text-white">
				<a href="#">로그 아웃</a>
			</h5>
		</div>
	</c:if>
</div>
<script>
	$(document).ready(function() {
		$("#searchinput").on('click', function() {
			$("#searchclear").removeClass("d-none");
		});
		
		$("#searchclear").on('click', function() {
			$("#searchinput").val('');
			$("#searchclear").addClass("d-none");
		});
	});
</script>