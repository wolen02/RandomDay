<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>무작위하루 시작페이지</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"/>
<link rel="stylesheet" href="/static/css/style.css" type="text/css">
</head>
<body>

	<div id="wrap">
	
	
		<header class="d-flex justify-content-end">
			<div class="main-box userInfo">
				<div>
				<c:choose>
					<c:when test="${userName != null }">
						<c:choose>
							<c:when test="${imagePath != null }">
								<a href="/randomday/user/userpage/view"><img class="mx-2 mb-1 rounded-circle" width="30px" height="30px" src="${imagePath }"></a>
							</c:when>
							<c:otherwise>
								<a href="/randomday/user/userpage/view"><img class="mx-2 mb-1 rounded-circle" width="30px" height="30px" src="/static/images/사용자 아이콘.png"></a>
							</c:otherwise>
						</c:choose>
							${userName }님, 반갑습니다. 
						<a href="/randomday/signout">로그아웃</a>
					</c:when>
					
					<c:otherwise> 
						<a href="/randomday/signin/view">로그인</a>
					</c:otherwise>
				</c:choose>

				</div>
			</div>
		</header>
		
		
		<section class="d-flex justify-content-center align-items-center height500">
			<div>
				<div class="mb-4 flex-column main-box animate__jello start_logo">
				
					<div id="domesticBtn" class="d-flex justify-content-center text-white mt-5 main-box btn">
						<h1 class="text-center mb-5 mainBanner">RAND</h1>
						<img width="150px" height="150px" src="/static/images/지구.png" id="earth" alt="지구">
						<h1 class="text-center mb-5 mainBanner">M&nbsp;DAY</h1>
					</div>
					
				</div>
				<div class="d-flex justify-content-center start animate__animated animate__tada animate__delay-1s animate__infinite	infinite">
					시작하려면 로고를 클릭해주세요
				</div>
			</div>
		</section>
		
		
		
		<c:import url="/WEB-INF/jsp/randomday/include/footer.jsp" />

	</div>

<script>

	$(document).ready(function(){
		
		// 애니메이션 효과
		
		const element = document.querySelector('.start_logo');
		element.classList.add('animate__animated', 'animate__jello'); // 엘리먼트에 애니메이션 클래스 부여
		
		// animate__animated클래스를 부여하면 자동으로 등록되는 커스텀 이벤트
		element.addEventListener('animationend', () => {
		  // 애니메이션 실행이 끝나면 함수 실행
		});
		

		

		
		
		$("#domesticBtn").on("click", function(){
			location.href="/randomday/domestic/select";
		});
		

		
		
	});


</script>
</body>
</html>