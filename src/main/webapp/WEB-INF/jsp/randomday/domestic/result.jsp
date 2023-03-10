<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>검색결과</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
<link rel="stylesheet" href="/static/css/style.css" type="text/css">
</head>
<body>

	<div id="wrap" class="bg-info">
		
		<c:import url="/WEB-INF/jsp/randomday/include/header.jsp"></c:import>
		
		
		<i class="bi bi-arrow-clockwise"></i>
		
		<section class="bg-success">
			
			<div class="bg-danger d-flex flex-wrap justify-content-center">
				
				<c:forEach var="result" items="${results }">
				
					<div class="card my-3 mx-3">
						<a class="text-decoration-none text-dark" href="/randomday/domestic/detail/view">
							<img class="card-img-top" src="${result.imageURL }" alt="${result.title }">
						  	
						  	<div class="card-body">
						    	<p class="card-text">${result.title}<br></p>
						  	
						  	</div>
						</a>
					 </div>
				</c:forEach>
				
				
				
		
		</section>
		
		
		<c:import url="/WEB-INF/jsp/randomday/include/footer.jsp"></c:import>
		
	
	</div>
	

</body>
</html>