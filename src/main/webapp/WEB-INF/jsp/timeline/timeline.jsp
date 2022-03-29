<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="wrap">
	<div class="mt-3">
		<textarea  placeholder="게시할 내용을 입력해주세요" class="form-control" name="content"></textarea>
		<div class="d-flex justify-content-between mt-2">
			<div class= d-flex>
				<input type="file" class="d-none" id="file" name="file">
				<a id="fileUploadBtn" href="#"><img alt="imageUpload" src="/images/image.jpg" width="35" height="35"></a>
				
				<%-- 업로드 된 임시 파일 이름 저장될 곳 --%>
				<div id="fileName" class="ml-2 mt-2">
				</div>
			</div>
			<button type="button" id="savePostBtn"class=" btn btn-info">게시</button>
		</div>
		

		<c:forEach items="${postList }" var="post">
		<div id="card" class="border border-3 mt-5">
			<div class="userCardName ">
				<span class="ml-3">
					${loginId}
				</span>
			</div>
			<div class="d-flex justify-content-center border border-3">
				<img src="${post.imagePath}" width="300px" height="300px">
			</div>
			<div class="mt-2 ml-3">
				<span><span class="font-weight-bold">글쓴이명:</span> ${post.content }</span>
			</div>
			
			<%--댓글 쓰기 --%>
			<%-- 로그인 된 상태에서만 쓸 수 있다. --%>
			<c:if test="${not empty userId }">
			<div class="comment-write d-flex border-top mt-2">
				<input type="text" id="comment${post.id}"class="form-control border-0" placeholder="댓글" >
				<button type="button" class="commentBtn btn btn-light" data-post-id="${post.id}">게시</button>
			</div>
			</c:if>
		</div>
		</c:forEach>
		
		
		
		
	</div>
</div>

 <script>
 	$(document).ready(function(){
 		// 파일 업로드 버튼을 눌렀을 때
 		$("#fileUploadBtn").on('click', function(e) {
 			e.preventDefault();	// 위로 올라가는 형상 방지
 			$('#file').click(); // input file을 클릭한 것과 같은 효과
 		});
 		// 사용자가 파일 업로드를 했을 때 유효성 확인 및 업로드 된 파일 이름 노출
 		$('#file').on('change', function(e)	{
 			// this
 			
 			let fileName = e.target.files[0].name; 
 			let fileArr = fileName.split(".");
 			/* console.log(fileName);
 			console.log(fileArr); */
 			
 			//확장자 유효성 체크
 			if(fileArr.length < 2 || 
 					(fileArr[fileArr.length - 1] != 'gif' && 
 					fileArr[fileArr.length - 1] != 'png' &&
 					fileArr[fileArr.length - 1] != 'jpeg' &&
 					fileArr[fileArr.length - 1] != 'jpg')) {
 				alert("이미지파일만 업로드해주세요");
 				$(this).val(''); // 파일이 서버에 넘어가지 않도록 비워줌
 				$('#fileName').text(''); // 파일명도 비워줌 태그이므로 text로 지워준다 val() (x)
 				return;
 			}
 			$('#fileName').text(fileName);
 		});
 		
 		// 게시버튼을 눌렀을때
 		$('#savePostBtn').on('click', function(e){
 			let content = $('textarea[name=content]').val();
			let fileName = $('#file').val();
			
			console.log(fileName);
			console.log(content);
			
 			if(content == ''){
 				alert("내용을 입력해주세요");
 				return;
 			}
 			if(fileName == ''){
 				alert("사진을 업로드해주세요")
 				return;
 			}
 			
 			let formData = new FormData();
 			formData.append("content",content);
			formData.append("file", $('input[name=file]')[0].files[0]);
			
			// ajax 서버 통신
			// enctype(암호화)
			
			$.ajax({
				type:"post"
				, url: "/post/create"
				, data: formData
				, enctype: "multipart/form-data"	//파일 업로드를 위한 필수 설정 
				, processData: false	// 파일 업로드를 위한 필수 설정 String이 아니기 떄문에 String으로 만들지 말아라라는 뜻
				, contentType: false 	// 파일 업로드를 위한 필수 설정 String이 아니기 떄문에 String으로 만들지 말아라라는 뜻 
				, success: function(data) {
					if(data.result == "success"){
						alert("게시 성공");
						location.href = "/timeline/timeline_view";
					} else {
						alert(data.errorMessage);
					}
				}
				, error: function(e) {
					alert("게시에 실패했습니다. 관리자에게 문의해주세요")
				}
			});
 			 			
 		});
 			
 		// 댓글 쓰기
 		 $('.commentBtn').on('click', function(){
 			let postId = $(this).data('post-id'); // 포스트 아이디 값을 가지고 온다.
 			//alert(postId);
 			
 			//let commentContent = $('#comment' + postId).val().trim();
 			// 댓들 내용 가지고 오기
 			let commentContent = $(this).siblings('input').val().trim(); // 형제를 가지고온다 div안에있는 태그들
 			
 			
 			if(commentContent == ''){
 				alert("댓글을 입력해주세요");
 				return;
 			}
 			// TODO
 			// ajax
 			$.ajax({
 				type:"POST"
 				, url: "/comment/create"
 				, data: {"postId":postId, "content":commentContent}
 				, success: function(data) {
 					if(data.result == "success") {
 						location.reload();
 					} else {
 						alert(data.error_Message);
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