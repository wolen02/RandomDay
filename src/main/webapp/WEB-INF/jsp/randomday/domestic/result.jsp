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
		
		
		
		<div id="refreshBtn" class="btn">
			<i class="bi bi-arrow-clockwise"></i>		
		</div>
		
		

		
		<section class="bg-success">
			
			<div class="bg-danger d-flex flex-wrap justify-content-center">
				
				<c:forEach var="result" items="${results }">

					<div class="card my-3 mx-3">

						<div class="d-flex justify-content-around">
							<div>${result.placeId }</div>
							

								<div class="btn pinBtn" data-pin-id="${result.placeId }"><i class="bi bi-pin"></i></div>
								<div class="btn pinFillBtn none" data-pin-fill-id="${result.placeId }"><i class="bi bi-pin-fill"></i></div>
							
							
							
							<c:choose>
								<c:when test="${!result.like }">
									<div class="btn heartBtn" data-heart-id="${result.placeId }"><i class="bi bi-suit-heart"></i></div>
									<div class="btn heartFillBtn none" data-heart-fill-id="${result.placeId }"><i class="bi bi-suit-heart-fill"></i></div>
								</c:when>
								
								<c:otherwise>
									<div class="btn heartBtn none" data-heart-id="${result.placeId }"><i class="bi bi-suit-heart"></i></div>
									<div class="btn heartFillBtn" data-heart-fill-id="${result.placeId }"><i class="bi bi-suit-heart-fill"></i></div>
								</c:otherwise>
							</c:choose>
							
							
						</div>
						
						
						<a class="text-decoration-none text-dark" href="/randomday/domestic/detail/view?placeId=${result.placeId }">
							<img class="card-img-top" src="${result.imageURL }" alt="${result.title }">
						  	
						  	<div class="card-body">
						    	<p class="card-text">${result.title}<br></p>
						  	
						  	</div>
						
						</a>
					 
					 </div>
					 
				</c:forEach>
				
			</div>
		
		</section>		
	

		<c:import url="/WEB-INF/jsp/randomday/include/footer.jsp"></c:import>
	
	
	</div>
	
<script>
	
	
	$(document).ready(function(){
		
		// 새로고침 버튼을 누르면 재검색
		$("#refreshBtn").on("click", function(){
			
			location.reload();
			
		});
		
		
				
		
		// 고정 버튼 누르면 고정 핀 색이 칠해지고 새고로침의 영향을 안받게 설정
		$(".pinFillBtn").on("click", function(){
			
			let id = $(this).data("pin-fill-id");
			
			// unpin api 불러오기
			$.ajax({
				type:"get"
				, url:"/unPin"
				, data:{"placeId": id}
				, success:function(data){
					if(data.result == "success"){
						
						$("[data-pin-fill-id = '" + id +"']").addClass(" none");
						$("[data-pin-id = '" + id + "']").removeClass(" none");
						
					}else{
						alert("고정해제 실패");
					}
				}
				, error:function(){
					alert("고정해제 오류");
					return;
				}
			});
			
			
		});
		
		
		// 고정 버튼 누르면 고정 핀 색이 칠해지고 새고로침의 영향을 안받게 설정
		$(".pinBtn").on("click", function(){
			
			let id = $(this).data("pin-id");
			
			// pin api 불러오기
			$.ajax({
				type:"get"
				, url:"/pin"
				, data:{"placeId": id}
				, success:function(data){
					if(data.result == "success"){
						
						$("[data-pin-id = '" + id +"']").addClass(" none");
						$("[data-pin-fill-id = '" + id + "']").removeClass(" none");
						
					}else{
						alert("고정 실패");
					}
				}
				, error:function(){
					alert("고정 오류");
					return;
				}
			});
			
		});
		
		
		
		// 채워진 하트 버튼을 누르면 좋아요 리스트에서 제외되고 하트가 비워짐
		$(".heartFillBtn").on("click", function(){
			
			let id = $(this).data("heart-fill-id");
			 

			 $.ajax({
					type:"get"
					, url:"/place/unLike"
					, data:{"placeId":id}
					, success:function(data){
						if(data.result == "success"){
							
							// 하트 비워짐
							
							 $('[data-heart-fill-id="' + id + '"]').addClass(" none");
							 
							 $('[data-heart-id="' + id + '"]').removeClass(" none");
							
						}else{
							alert("좋아요 취소 실패");
							return;
						}
					}
					, error:function(){
						alert("좋아요 취소 오류");
						return;
					}
				});
			
			
			
		});		
		
		
		// 빈 하트 버튼을 누르면 좋아요 리스트에 추가되고 하트가 채워짐
		$(".heartBtn").on("click", function(){
			
			let id = $(this).data("heart-id");


			
				$.ajax({
					type:"get"
					, url:"/place/like"
					, data:{"placeId":id}
					, success:function(data){
						if(data.result == "success"){
							
							// 하트 채워짐
							
							 $('[data-heart-id="' + id + '"]').addClass(" none");
							 
							 $('[data-heart-fill-id="' + id + '"]').removeClass(" none");
							
							
						}else{
							alert("좋아요 실패");
							return;
						}
					}
					, error:function(){
						alert("좋아요 오류");
						return;
					}
				});
			
			
		});
		
		
		
	});
	
	
	
</script>
</body>
</html>