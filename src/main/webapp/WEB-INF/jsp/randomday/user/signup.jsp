<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
<link rel="stylesheet" href="/static/css/style.css" type="text/css">
</head>
<body>

	<div id="wrap">
		
		<header class="mt-3">
			<div class="d-flex justify-content-between">
				<h2><b>무작위하루</b></h2><span>주지성님</span>
			</div>
		</header>
		
		<section class="d-flex justify-content-center align-items-center">
			
			<div class="signup_box d-flex justify-content-center align-items-center">
				
				<div>
					
					<div class="d-flex">
						<input type="text" id="inputId" class="form-control input_box2 mx-3" placeholder="아이디">
						<button type="button" id="duplicatedBtn" class="btn btn-primary"><span class="small"><b>중복확인</b></span></button>
					</div>
					
					<input type="password" id="inputPass" class="form-control mt-2 input_box2 mx-3" placeholder="비밀번호">
					<input type="password" id="inputConfirmPass" class="form-control mt-2 input_box2 mx-3" placeholder="비밀번호 확인">
					<input type="text" id="inputName" class="form-control mt-2 input_box2 mx-3" placeholder="이름">
					
					<div class="d-flex mt-2">
						<input type="text" id="inputBirth" class="form-control input_birth_box mx-3" placeholder="생년월일 ex)19991229">
						<select id="inputSex" class="form-select input_sex_box" aria-label="성별">
					  		<option selected disabled id="sexSelection">성별</option>
						  	<option value="man">남</option>
						  	<option value="woman">여</option>
						</select>
					</div>
					
					<input type="text" id="inputEmail" class="form-control mt-2 input_box2 mx-3" placeholder="이메일">
				
					
					<div class="d-flex justify-content-center">
						<button type="button" id="signupBtn" class="btn btn-primary form-control mt-4">
						<span class="text-white"><b>회원가입</b></span>
						</button>
					</div>
					
					<div class="text-white small text-center mt-2">이미 아이디가 있으신가요?</div>
					
					<div class="text-center mt-2"><a href="/randomday/user/signin/view">로그인</a></div>
					
				</div>
				
				
			</div>
			
			
			
		</section>
		
		
		<c:import url="/WEB-INF/jsp/randomday/include/footer.jsp" />
	
	</div>
	
<script>
	$(document).ready(function(){
		
		
		var isDuplicate = true;
		
		
		// 중복확인 버튼을 누른 후 아이디를 변경할 때
		$("#inputId").on("keydown", function(){
			isDuplicate = true;
		});

		
		
		// 중복확인 버튼을 눌렀을 때
		$("#duplicatedBtn").on("click", function(){
			
			isDuplicate = true;
			
			
			let id = $("#inputId").val();
			
			$.ajax({
				type:"get"
				, url:"/user/duplicate_id"
				, data:{"loginId":id}
				, success:function(data){
					if(data.result){
						alert("이미 존재하는 아이디입니다.");
						return;
					}else{
						alert("사용가능한 아이디입니다.");
						isDuplicate = false;
						return;
					}
				}
				, error:function(){
					alert("중복확인 오류");
					return;
				}
			});
			
		});
		
		
		// 성별 선택지에서 성별 선택지 안보이게 설정
		$("#sexSelection").hide();
		
		
		// 회원가입 버튼을 눌렀을 때
		
		$("#signupBtn").on("click", function(){
			
			let id = $("#inputId").val();
			let pass = $("#inputPass").val();
			let confirmPass = $("#inputConfirmPass").val();
			let name = $("#inputName").val();
			let birth = $("#inputBirth").val();
			let sex = $("#inputSex").val();
			let email = $("#inputEmail").val();
			
			if(id == ""){
				alert("아이디를 입력해주세요");
				return;
			}
			
			if(pass == ""){
				alert("비밀번호를 입력해주세요");
				return;
			}
			
			if(confirmPass == ""){
				alert("비밀번호 확인란을 입력해주세요");
				return;
			}
			
			if(name == ""){
				alert("이름을 입력해주세요");
				return;
			}
			
			if(birth == ""){
				alert("생년월일을 입력해주세요");
				return;
			}
			
			if(sex != "man" && sex != "woman"){
				alert("성별을 선택해주세요");
				return;
			}
			
			if(email == ""){
				alert("이메일을 입력해주세요");
				return;
			}
			
			if(isDuplicate){
				alert("아이디 중복확인을 해주세요");
				return;
			}
			
			if(pass != confirmPass){
				alert("비밀번호가 같지 않습니다");
				return;
			}
			
			
			$.ajax({
				type:"post"
				, url:"/user/signup"
				, data:{"loginId":id, "password":pass, "name":name, "birth":birth, "sex":sex, "email":email}
				, success:function(data){
					if(data.result == "success"){
						location.href="/randomday/domestic/select/view"
					}else{
						alert("회원가입 실패");
					}
				}
				, error:function(){
					alert("회원가입 오류");
					return;
				}
				
			});
			
			
			
			
			
		});
		
		
		
	});

</script>

</body>
</html>