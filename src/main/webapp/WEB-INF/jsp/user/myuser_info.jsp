<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="container d-flex  mt-5">
	<table class="user-info table table-bordered  text-center">
			<tr>
				<th>아이디</th>
				<td>${loginId}
			</tr>
			<tr>
				<th>이름</th>
				<td>${userName}</td>
			</tr>
			<tr id="pwd">
				<th>비밀번호 </th>
				<td><button type="button" class="btn btn-info" id="changePwdBtn">비밀번호 변경</button></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td>${userEmail}</td>
			</tr>
			
			<%-- 비밀번호 변경 --%>
			<tr id="changePwdArea"class="d-none">
				<th>
					비밀번호
				</th>
				<td>
					<div>
						<div class="d-flex justify-content-between align-items-center col-10">
							<label for="currentPwd">현재 비밀번호</label>
							<input type="password" id="currentPwd" name="currentPwd" class="form-control col-5 " placeholder="현재 비밀번호">
						</div>
						<div class="d-flex justify-content-between  align-items-center col-10  mt-3">
							<label for="newPwd" class="mr-3">새로운 비밀번호</label>
							<input type="password" id="newPwd" name="newPwd" class="form-control col-5" placeholder="새로운 비밀번호">
						</div>
						<div class="d-flex justify-content-between align-items-center col-10  mt-3">
							<label for="checknewPwd">새로운 비밀번호 확인</label>
							<input type="password" id="checknewPwd" name="checknewPwd" class="form-control col-5" placeholder="새로운 비밀번호 확인">
						</div>
						<div class="d-flex justify-content-end mt-3">
							<button type="button" class="btn btn-dark mr-3" id="changePwdComplete">완료</button>
							<button type="button" class="btn btn-dark mr-3" id="changeCanclePwd">취소</button>
						</div>
					</div>
				</td>
				
			</tr>
			
			
	</table>

</div>

<script>
	$(document).ready(function(){
		//비밀번호 변경 버튼 클릭 시
		$("#changePwdBtn").on('click', function(){
			$('#changePwdArea').removeClass('d-none'); 
			$('#pwd').addClass('d-none'); // 숨김
		});	
		
		//비밀번호 취소 버튼 클릭시
		$("#changeCanclePwd").on('click', function(){
			$('#changePwdArea').addClass('d-none'); 
			$('#pwd').removeClass('d-none'); // 숨김
		});
		
		
		// 비밀번호 변경 완료 버튼 클릭 시  
		$("#changePwdComplete").on('click', function(){
			// 현재 비밀번호
			let currentPwd = $('#currentPwd').val().trim();
			// 새로운 비밀번호
			let newPwd = $('#newPwd').val().trim();
			// 새로운 비밀번호 확인
			let checknewPwd = $('#checknewPwd').val().trim();
			
			if(currentPwd == ""){
				alert("현재 비밀번호를 입력하세요");
				return;
			}
			
			if(newPwd == ""){
				alert("신규 비밀번호를 입력하세요");
				return;
			}
			
			if(checknewPwd == ""){
				alert("신규 비밀번호 확인을 입력하세요");
				return;
			}
			
			if(newPwd != checknewPwd) {
				alert("신규 비밀번호와 신규 비밀번호 확인이 서로 다릅니다. 다시 입력해주세요")
				$('#newPwd').val("");
				$('#checknewPwd').val("");
				return;
			} 
		
		
		// 데이터들을 폼 태그로 변경
		let formData = new FormData();
		formData.append("password", currentPwd);
		formData.append("newPassword", newPwd);
		
		 $.ajax({
			type:"post"
			, url: "/user/changePwd"
			, data: formData
			, processData: false	
			, contentType: false
			, success: function(data) {
				if(data.result == "success"){
					alert("비밀번호가 변경되었습니다.");
					location.href = "/user/sign_in_view";
				} else {
					alert(data.error_message );
					$('#currentPwd').val("");
					$('#newPwd').val("");
					$('#checknewPwd').val("");
				}
			}
			, error: function(jqXHR, textStatus, errorThrown) { // 에러를 세부적으로 찍어준다
 					let errorMsg = jqXHR.responseJSON.status;
 					alert(errorMsg + ":" + textStatus);
 			}
		});
	});
});
</script>