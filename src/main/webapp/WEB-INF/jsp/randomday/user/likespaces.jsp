<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>좋아요 목록</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
<link rel="stylesheet" href="/static/css/style.css" type="text/css">
</head>
<body>

	<div id="wrap" class="height800">
		
		<c:import url="/WEB-INF/jsp/randomday/include/header.jsp"></c:import>
		
		<section class="d-flex justify-content-center">
			
			<div>
				
				<h3 class="LOTTERIACHAB text-white">내가 좋아요 한 장소들</h3>
				<br>
				
				<div>
				
					<table class="table text-center like-table shadow PuradakGentleGothicR">
						
						<thead>
							<th>이름</th>
							<th>분류</th>
							<th>&nbsp;&nbsp;&nbsp;</th>
							<th>주소</th>
							<th>삭제</th>
						</thead>
						
						<tbody>
						
							<c:forEach var="place" items="${places }" varStatus="status">
							
								<tr>
								
									<td>${place.name }</td>
									<td>${place.category }</td>
									<td>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</td>
									<td>${place.roadAddress }</td>
									<td>&nbsp&nbsp<button class="btn btn-success deleteBtn shadow" data-btn-id="${placesId[status.index] }">삭제</button></td>
								</tr>	
								
								
							</c:forEach>
						
							
						</tbody>

					</table>
				
				
				</div>
				
			
			</div>
			
		
		</section>
		
		<c:import url="/WEB-INF/jsp/randomday/include/footer.jsp"></c:import>	
	
	</div>
<script>
	
	$(document).ready(function(){
	
		
		// 삭제 버튼 누를 때
		
		$(".deleteBtn").on("click", function(){
			
			
			let placeId = $(this).data("btn-id");
			

			$.ajax({
				type:"get"
				, url:"/place/unLike"
				, data:{"placeId":placeId}
				, success:function(data){
					if(data.result == "success"){
						location.reload();
					}else{
						alert("장소 삭제 실패");
						return;
					}
				}
				, error:function(){
					alert("장소 삭제 오류");
					return;
				}
				
			});
			
			
		});
	
		
		
	});

</script>
</body>
</html>