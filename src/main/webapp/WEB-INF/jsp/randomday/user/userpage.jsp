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
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js" integrity="sha384-mQ93GR66B00ZXjt0YO5KlohRA5SY2XofN4zfuZxLkoj1gXtW8ANNCe9d5Y3eG5eD" crossorigin="anonymous"></script>
<link rel="stylesheet" href="/static/css/style.css" type="text/css">
</head>
<body>

	<div id="wrap">
		
		<c:import url="/WEB-INF/jsp/randomday/include/header.jsp"></c:import>
	
		<section class="height500 d-flex justify-content-center">
			
			<div class="mypage box shadow">
				
				<!--  닉네임 수정  -->
				
				<div class="d-flex mypage_profile">
				
				<!--  프로필이 존재하면 해당 사진으로 존재 하지 않으면 기본 이미지로 -->				
					<c:choose>
						<c:when test="${imagePath != null }">
							<img class="mx-2 mb-1 card" src="${imagePath }"><div class="userName EF_jejudoldam">${userName } 님</div>
						</c:when>
						
						<c:otherwise>
							<img class="mx-2 mb-1 card" src="/static/images/사용자 아이콘.png"><div class="userName EF_jejudoldam">${userName } 님</div>
						</c:otherwise>
					</c:choose>

				
				</div>
				
				
				
				<a class="text-decoration-none" href="/randomday/user/likespaces/view">
					<div class="btn mx-4">
						<div class="mx-2 mypageBtn PuradakGentleGothicR"><i class="bi bi-heart-fill mx-2"></i>좋아하는 장소 목록</div>
					</div>
				</a>
				
				<a class="text-decoration-none" href="/randomday/user/modify/view">
					<div class=" btn mx-4">
						<div class="mx-2 mypageBtn PuradakGentleGothicR"><i class="bi bi-gear-fill mx-2"></i>회원 정보 수정</div>
					</div>
				</a>
				
				
			</div>
			
		
		</section>
	
	
		<c:import url="/WEB-INF/jsp/randomday/include/footer.jsp"></c:import>
	
	
		<div class="modal fade" id="photoModal" tabindex="-1" role="dialog" aria-labelledby="reviewModalLabel" aria-hidden="true">
			  <div class="modal-dialog" role="document">
			    <div class="modal-content d-flex justify-content-center align-items-center bg-transparent border-0">
			      
			      <div id="photoModalBody" class="modal-body">
			      
			      	<c:choose>
						<c:when test="${imagePath != null }">
							<img height="500px" src="${imagePath }">
						</c:when>
						
						<c:otherwise>
							<img height="500px" src="/static/images/사용자 아이콘.png">
						</c:otherwise>
					</c:choose>
			      
			    </div>
			  </div>
			</div>
	
	</div>
	
<script>
	$(document).ready(function(){
		
		// modal 에 관한 내용
		$(".card").on("click", function(){
			
			 $("#photoModal").modal("show");
			 
	            $(".close").click(function() {
	                $("#photoModal").modal("hide");
	            });
			
		});
		
	});
	
</script>
</body>
</html>