<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

<header class="mb-3">
	
	<div class="d-flex justify-content-between">
	
		<a class="text-decoration-none text-dark" href="/randomday/start/view">
			<div class="d-flex my-5 main-box">
			
				<h1 class="text-center mb-2 mainBanner"><b>R</b><b>A</b><b>N</b><b>D</b></h1>
				<img width="50px" height="50px" src="/static/images/지구.png" id="earth" alt="지구">
				<h1 class="text-center mb-2 mainBanner"><b>M</b>&nbsp;<b>D</b><b>A</b><b>Y</b></h1>			
			
			</div>
		</a>
		
		<div class="main-box userInfo">
			<div>
			<a href="/randomday/user/userpage/view"><img class="mx-2 mb-1" width="25px" src="/static/images/사용자 아이콘.png"></a>
			${userName }님, 반갑습니다. 
			<a href="/randomday/start/view">로그아웃</a>
			</div>
		</div>
		
	</div>
	
</header>