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
				
				<i class="bi bi-gear-fill"></i>
				
				
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