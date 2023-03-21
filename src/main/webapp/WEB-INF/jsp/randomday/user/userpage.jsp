<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>유저 페이지</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
<link rel="stylesheet" href="/static/css/style.css" type="text/css">
</head>
<body>

	<div id="wrap">
		
		<c:import url="/WEB-INF/jsp/randomday/include/header.jsp"></c:import>
	
		<section class="bg-success height500 d-flex justify-content-center">
			
			<div>
				
				<!--  닉네임 수정  -->
				
				<div class="d-flex text-white">
				
				<!--  프로필이 존재하면 해당 사진으로 존재 하지 않으면 기본 이미지로 -->
					<img class="mx-2 mb-1" width="25px" src="/static/images/사용자 아이콘.png"><div>${userName } 님</div><i class="bi bi-gear-fill"></i>
				</div>
				
				
				
				<a class="text-decoration-none text-white" href="/randomday/user/likespaces/view">
					<div class="d-flex btn text-white">
					
		
					
						<i class="bi bi-heart-fill"></i>
						<div>좋아하는 장소 목록</div>

					
					</div>
				</a>
				
				<a class="text-decoration-none text-white" href="/randomday/user/modify/view">
					<div class="d-flex btn text-white">
					
						<i class="bi bi-gear-fill"></i>
						<div>회원 정보 수정</div>
					
					</div>
				</a>
				
				
			</div>
			
			
		
		</section>
	
	
		<c:import url="/WEB-INF/jsp/randomday/include/footer.jsp"></c:import>
	
	</div>

</body>
</html>