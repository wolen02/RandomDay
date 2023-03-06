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
<link rel="stylesheet" href="/static/css/style.css" type="text/css">
</head>
<body>

	<div id="wrap">
	
	
		<header></header>
		
		
		<section class="d-flex justify-content-center align-items-center height500">
			<div class="mb-4 flex-column main-box">
			
					<div class="d-flex justify-content-center mt-5 main-box">
						<h1 class="text-center mb-5 mainBanner"><b>R</b><b>A</b><b>N</b><b>D</b></h1>
						<img width="150px" height="150px" src="/static/images/지구.png" id="earth" alt="지구">
						<h1 class="text-center mb-5 mainBanner"><b>M</b>&nbsp;<b>D</b><b>A</b><b>Y</b></h1>
					</div>


				
					<div class="d-flex justify-content-around mt-5 main-box">
						<button type="button" id="domesticBtn" class="btn btn-circle btn-xl"><span class="text-white"><b>국내</b></span></button>
						<button type="button" id="foreignBtn" class="btn btn-circle btn-xl"><span class="text-white"><b>해외</b></span></button>
					</div>

				
			
			</div>
		</section>
		
		<div class="d-flex justify-content-center align-items-end end">
			<c:import url="/WEB-INF/jsp/randomday/include/footer.jsp" />
		</div>
	
	

	</div>

<script>

	$(document).ready(function(){
		
		$("#domesticBtn").on("click", function(){
			location.href="/randomday/domestic/select";
		});
		
		
		$("#foreignBtn").on("click", function(){
			location.href="/randomday/user/signin/view";
		});
		
		
		
	});


</script>
</body>
</html>