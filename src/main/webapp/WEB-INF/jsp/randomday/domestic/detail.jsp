<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>세부정보</title>
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

   		 	
   		 	<section class="d-flex justify-content-around">
   		 		
   		 		<c:choose>
   		 			<c:when test="${place.getImageURL() != null }">
   		 				<div>
   		 					<img id="image" class="image" width="500px;" height="400px;" src="${place.getImageURL() }">
   		 				</div>
   		 			</c:when>
   		 			
   		 			<c:otherwise>
   		 				<div>
		   		 			<img class="image" width="500px;" height="400px;" src="data:image/jpeg;base64,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">
		   		 		</div>
   		 			</c:otherwise>
   		 		</c:choose>
   		 		
   		 		
   		 		<div class="mx-5 placeContent">
   		 			
   		 			<div class="d-flex">
   		 				<h1 class="mx-2 mt-2">${place.getName() }</h1>
			   		 		
			   		 		<c:choose>
								<c:when test="${!place.isLike() }">
									<div class="btn heartBtn heartSize mt-2" data-heart-id="${placeId }"><i class="bi bi-suit-heart"></i></div>
									<div class="btn heartFillBtn none heartSize mt-2" data-heart-fill-id="${placeId }"><i class="bi bi-suit-heart-fill"></i></div>
								</c:when>
								
								<c:otherwise>
									<div class="btn heartBtn none heartSize mt-2" data-heart-id="${placeId }"><i class="bi bi-suit-heart"></i></div>
									<div class="btn heartFillBtn heartSize mt-2" data-heart-fill-id="${placeId }"><i class="bi bi-suit-heart-fill"></i></div>
								</c:otherwise>
							</c:choose>
   		 				
   		 			</div>
   		 			
   		 			<div class="mx-2">카테고리: <span id="menu">${place.getCategory() }</span></div>
   		 			
   		 			<div class="d-flex mx-2 mt-2">
   		 				<a id="showReview" class="mx-2" href="#">리뷰(${reviewCount })</a>
   		 				<a class="mx-2" href="#">좋아요(${likeCount })</a>
   		 			</div>
   		 			
   		 			<div class="my-3 mx-2">

   		 				<h3>위치</h3>
   		 				<div class="bg-success height220 d-flex justify-content-between">
   		 					
   		 					<div>지도자리</div>
   		 					
   		 					<div class="d-flex justify-content-end align-items-end height220">
		   		 				<button type="button" id="reviewBtn" class="btn btn-secondary" data-toggle="modal" data-target="#exampleModal">
								  리뷰작성하기
								</button>
		   		 			</div>
   		 				</div>
   		 				
   		 			</div>
   		 			
   		 			

   		 			
   		 		</div>

   		 	
   		 	
   		 	</section>
   		 	
   		 	<c:import url="/WEB-INF/jsp/randomday/include/footer.jsp"></c:import>
		
		<!--  리뷰 작성 모달 -->
			<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
			  <div class="modal-dialog" role="document">
			    <div class="modal-content">
			    
			      <div class="modal-header">
			      	<div id="placeId">${placeId }</div>
			        <h5 class="modal-title" id="exampleModalLabel">리뷰작성</h5>
			        <button type="button" class="close btn btn-primary" data-dismiss="modal" aria-label="Close">
			          <i class="bi bi-x" style="text-size:10px;"></i>
			        </button>
				  
			      </div>
			      <div class="modal-body">
			      
				     <!-- 별점 매기기 -->
				     <div class="d-flex">
				     	<P id="star">
						  <a href="#" value="1"><i class="bi bi-star-fill"></i></a>
						  <a href="#" value="2"><i class="bi bi-star-fill"></i></a>
						  <a href="#" value="3"><i class="bi bi-star-fill"></i></a>
						  <a href="#" value="4"><i class="bi bi-star-fill"></i></a>
						  <a href="#" value="5"><i class="bi bi-star-fill"></i></a>
						 <p>
						 
						 <div class="point-box">
						 	<div class="point_score"></div>
						 </div>
				     </div>
					 
					
			       	<textarea id="content" class="form-control"></textarea>
			       	
			      </div>
			      
			      <div class="modal-footer">
			        <button type="button" id="saveReviewBtn" class="btn btn-primary">리뷰 작성</button>
			        <button type="button" class="btn btn-secondary close" data-dismiss="modal">닫기</button>
			      </div>
			      
			    </div>
			  </div>
			</div>
			
			<!--  리뷰 모달 -->
			
			<div class="modal fade" id="reviewModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
			  <div class="modal-dialog" role="document">
			    <div class="modal-content">
			    
			      <div class="modal-header">
			      	<div id="placeId">${placeId }</div>
			        <h5 class="modal-title" id="exampleModalLabel">${place.getName() }</h5>
			        <button type="button" class="close btn btn-primary" data-dismiss="modal" aria-label="Close">
			          <i class="bi bi-x" style="text-size:10px;"></i>
			        </button>
			      </div>
			      
			      <div class="modal-body">
			      
			      	<table class="table">
			      		<thead>
			      			<tr>
				      			<th>작성자</th>
				      			<th>내용</th>
				      			<th>별점</th>
			      			</tr>
			      		</thead>
			      		<tbody>
					      	
					       	<c:forEach var="review" items="${reviews }">
					       		
					       		<tr>
					      			<td>${review.getUserName() }</td>
					      			<td>${review.getContent() }</td>
					      			<td>${review.getPoint() }</td>
					      		</tr>
					       	
					       	</c:forEach>
			      		</tbody>
			      	</table>
			      </div>
			      
			    </div>
			  </div>
			</div>
			
			
		
<script>
	
		$(document).ready(function(){
			
			var point = 0;
			
			// 별점주기
			$('#star a').click(function(){ 
				 $(this).parent().children("a").removeClass("on");    
				 $(this).addClass("on").prevAll("a").addClass("on");
				 
				 point = $(this).attr("value");
			 });
			
			// 마우스 올렸을 때 별 색칠되기
			$("#star a").mousemove(function(){
				 $(this).parent().children("a").removeClass("on");    
				 $(this).addClass("on").prevAll("a").addClass("on");
				 
				 
				 // 별점 점수로 시각화
				 point = $(this).attr("value");
				 
				 $(".point_score").text(point + ".0");
				 
			  });
			
			// 마우스 안올렸을 때 별 색칠 취소(클릭하면 작동안되게 하기)
			if(point = 0){
				$("#star a").mouseout(function(){
					 $(this).parent().children("a").removeClass("on");    
				
				  });
			}
			

			
			// review 얻어오기
			$("#showReview").on("click", function(){
				
				$("#reviewModal").modal("show");
				
	            $(".close").click(function() {
	                $("#reviewModal").modal("hide");
	            });
				
				
			});
			

			// review 작성
			$("#saveReviewBtn").on("click", function(){
				
				let content = $("#content").val();
				let placeId = $("#placeId").text();
				
				$.ajax({
					type:"post"
					, url:"/add/review"
					, data:{"placeId":placeId,"point":point, "content":content}
					, success:function(data){
						if(data.result == "success"){
							// 리뷰 작성 완료표시와 함께 모달 창 닫기
							alert("리뷰가 성공적으로 작성되었습니다.");
							 $("#exampleModal").modal("hide");
							 location.reload();
						}else{
							alert("리뷰 작성 실패");
							return;
						}
					}
					, error:function(){
						alert("리뷰작성 오류");
						return;
					}
				});
				
			});
			
			
			
			
			// modal 에 관한 내용
			$("#reviewBtn").on("click", function(){
				
				 $("#exampleModal").modal("show");
				 
		            $(".close").click(function() {
		                $("#exampleModal").modal("hide");
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
								 
								 location.reload();
								
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

				let doName = $("#doName").text();
				let guName = $("#guName").text();
				let menuName =  $("#menu").text();
				let imagePath = $("#image").text();
				
					$.ajax({
						type:"get"
						, url:"/place/like"
						, data:{"placeId": id, "doName":doName, "guName":guName, "menuName": menuName, "imagePath":imagePath}
						, success:function(data){
							if(data.result == "success"){
								
								// 하트 채워짐
								
								 $('[data-heart-id="' + id + '"]').addClass(" none");
								 
								 $('[data-heart-fill-id="' + id + '"]').removeClass(" none");
								
								 location.reload();
								 
								
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