<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 수정</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
<link rel="stylesheet" href="/static/css/style.css" type="text/css">
</head>
<body>

	<div id="wrap">
		
		<c:import url="/WEB-INF/jsp/randomday/include/header.jsp"></c:import>
	
	
		<section class="d-flex justify-content-center align-items-center">
			
			<div class="modify-box box shadow">
				
				<div class="mx-4 my-4">
					
					<h3 class="text-white">프로필 변경</h3>
					
					<c:choose>
						<c:when test="${imagePath == null }">
							<img class="mx-2 my-2" width="50px" height="50px" src="/static/images/사용자 아이콘.png">
						</c:when>
							
						<c:otherwise>
							<img class="mx-2 my-2 rounded-circle" width="50px" height="50px" src="${imagePath }">
						</c:otherwise>
					</c:choose>
					
					<input class="form-control" type="file" id="inputFile">
					<div class="d-flex justify-content-end my-3">
						<button id="imageChangeBtn" class="btn btn-secondary">프로필 변경</button>
					</div>
				
				</div>
				
				
				<div class="mx-4 my-4">
				
					<h3 class="text-white mb-3">비밀번호 변경</h3>
				
					<label class="text-white">현재 비밀번호 입력</label>
					<input id="confirmPass" class="form-control mb-2" type="password">
					
					<label class="text-white">새 비밀번호 입력</label>
					<input id="newPass" class="form-control" type="password">
					
					<div class="d-flex justify-content-end my-3">
						<button id="modifyBtn" class="btn passChangeBtn">비밀번호 변경</button>
					</div>
					
				</div>
				
			</div>
			
		</section>
	
	
		<c:import url="/WEB-INF/jsp/randomday/include/footer.jsp"></c:import>
		
	</div>


<script>
	
	$(document).ready(function(){
		
		
		// 프로필 변경
		$("#imageChangeBtn").on("click", function(){
			
			var formData = new FormData();
			
			formData.append("file", $("#inputFile")[0].files[0]);
			
			$.ajax({
				type:"post"
				, url:"/user/modify/profile"
				,data:formData
				, enctype:"multipart/form-data"
				, processData:false
				, contentType:false
				, success: function(data){
					if(data.result == "success"){
						location.href="/randomday/user/userpage/view";
					}else{
						alert("프로필 변경 실패");
						return;
					}
				}
				, error: function(){
					alert("프로필 변경 오류");
					return;
				}
			});
			
			
		});
		
		
		
		
		// 비밀번호 변경
		$("#modifyBtn").on("click", function(){
			
			let confirmPass = $("#confirmPass").val();
			let newPass = $("#newPass").val();
			
			if(confirmPass == ""){
				alert("비밀번호를 입력해주세요");
				return;
			}
			
			if(newPass == ""){
				alert("새로운 비밀번호를 입력해주세요");
				return;
			}
			
			$.ajax({
				type:"post"
				, url:"/user/modify/password"
				, data:{"confirmPassword": confirmPass, "newPassword": newPass}
				, success:function(data){
					if(data.result == "success"){
						alert("비밀번호가 성공적으로 변경되었습니다.");
						location.href="/randomday/user/userpage/view";
					}else{
						alert("비밀번호 변경 실패");
						return;
					}
				}
				, error:function(){
					alert("비밀번호 변경 오류");
					return;
				}
			});
			
		});		
		
		
	});
	
	
</script>
</body>
</html>