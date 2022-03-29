<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<div class="d-flex justify-content-center mt-5">
		<div class="login-box">
			<h1 class="mb-5 text-center ">Changstagram</h1>
			
			<%-- 키보드 Enter키로 로그인이 될 수 있도록 form 태그를 만들어준다.(submit 타입의 버튼이 동작됨) --%>
			<form id="loginForm" action="/user/sign_in" method="post">
				<div class="input-group ml-5 mb-3 col-9">
					<%-- input-group-prepend: input box 앞에 ID 부분을 회색으로 붙인다. --%>
					<div class="input-group-prepend">
						<span class="input-group-text">ID</span>
					</div>
					<input type="text" class="form-control" id="loginId" name="loginId">
				</div>
		
				<div class="input-group ml-5 mb-3 col-9">
					<div class="input-group-prepend">
						<span class="input-group-text">PW</span>
					</div>
					<input type="password" class="form-control" id="password" name="password">
				</div>
				
				<%-- btn-block: 로그인 박스 영역에 버튼을 가득 채운다. --%>
				<div class="d-flex justify-content-center">
					<input type="submit" id="loginBtn" class="btn btn-primary col-8 mt-5" value="로그인">
				</div>
				<div class="d-flex justify-content-center mt-3">
					<a class="btn  btn-dark col-8" href="/user/sign_up_view">회원가입</a>
				</div>
			</form>
		</div>
</div>

<script>
	$(document).ready(function(){
		$('#loginForm').on('submit', function(e){
			e.preventDefault();
			
			let loginId = $('#loginId').val().trim();
			let	password = $('#password').val().trim();
			
			if(loginId == "")	{
				alert("아이디를 입력해주세요");
				return;
			}
			
			if(password == ""){
				alert("비밀번호를 입력해주세요");
				return;
			}
			
			let url = $(this).attr('action');
			let params = $(this).serialize();
			
			// post(보낼 주소, 데이터들)
			$.post(url, params)
			.done(function(data){
				if(data.result == "success"){
					location.href = "/timeline/timeline_view";
				} else {
					alert(data.errorMessage)
				}
				
			});
		});
		
	});
</script>