<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
<link rel="stylesheet" href="/static/css/style.css" type="text/css">
</head>
<body>
	
	<div id="wrap">
		
		<header class="mt-5">
			<a class="text-decoration-none text-white" href="/randomday/start/view">
				<div class="d-flex justify-content-center my-3 main-box">
					<div class="text-center mainBanner">RAND</div>
					<img width="60px" height="60px" src="/static/images/지구.png" id="earth" class="mt-2" alt="지구">
					<div class="text-center mainBanner">M&nbsp;DAY</div>
				</div>
			</a>
		</header>
		
		
		<section class="d-flex justify-content-center align-items-center height500">
		
			<div class="login_box d-flex justify-content-center align-items-center">
			
				<div>
					
					<div class="d-flex justify-content-center">
						<div class="input_box">
							

							<div class="my-2 d-flex justify-content-center">
								<div>
									
									<input type="text" id="inputId" placeholder="아이디" class="form-control">
									<div id="idEmpty" class="text-white small">아이디를 입력해주세요</div>
									
								</div>
							</div>
							
							
							<div class="d-flex justify-content-center">
							
								<div class="my-2">
									<input type="password" id="inputPass" placeholder="비밀번호" class="form-control">
									<div id="passEmpty" class="text-white small">비밀번호를 입력해주세요</div>				
								</div>
							
							</div>
							
						</div>
					</div>
					
					
					<div>
						<button type="button" id="loginBtn" class="btn form-control mt-4 login_btn">로그인</button>
					</div>
					
					<div class="text-white text-center mt-3">아이디가 없으신가요?</div>
					<div class="text-center mt-2"><a href="/randomday/signup/view">회원가입</a></div>
				
			
				</div>
				

				
			</div>
			
		</section>
		
		
		<c:import url="/WEB-INF/jsp/randomday/include/footer.jsp" />
		
		
	</div>
	
<script>
	
	$(document).ready(function(){
		
		// 로그인 버튼 누르면 작동하는 기능
		
		$("#loginBtn").on("click", function(){
			
			let id = $("#inputId").val();
			let password = $("#inputPass").val();
			
			$("#idEmpty").hide();
			$("#passEmpty").hide();
			
			if(id == ""){
				$("#idEmpty").show();
				return;
			}
			
			if(password == ""){
				$("#passEmpty").show();
				return;
			}
			
			
			$.ajax({
				type:"post"
				, url:"/user/signin"
				, data:{"loginId":id, "password":password}
				, success:function(data){
					if(data.result == "success"){
						location.href="/randomday/start/view";
					}else if(data.result == "failId"){
						alert("아이디를 확인해주세요");
						return;
					}else if(data.result == "failPass"){
						alert("비밀번호를 확인해주세요");
						return;
					}else{
						alert("로그인 실패");
						return;
					}
				}
				, error:function(){
					alert("로그인 오류");
					return;
				}
				
				
			})
			
			
		});
		
		
	});
	
	
</script>
</body>
</html>