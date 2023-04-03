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

   		 	
   		 	<section class="d-flex justify-content-around mb-5 content">
   		 	
   		 		<div class="d-flex align-items-center mt-5">
   		 			<div id="roadAddress" class="none">${place.getRoadAddress() }</div>
	   		 			
		   		 			<c:choose>
		   		 			<c:when test="${place.getImageURL() != null }">
		   		 				<div>
		   		 					<img id="image" class="image" width="500px;" height="400px;" src="${place.getImageURL() }">
		   		 				</div>
		   		 			</c:when>
		   		 			
		   		 			<c:otherwise>
		   		 				<div>
				   		 			<img class="image" width="500px;" height="400px;" src="/static/images/이미지 존재하지 않음.png">
				   		 		</div>
		   		 			</c:otherwise>
		   		 			</c:choose>
   		 			
   		 			
   		 		</div>
   		 		
   		 		
   		 		
   		 		<div class="mx-5 placeContent">
   		 			
   		 			<div class="d-flex">
   		 				<h1 class="mx-2 mt-2 PuradakGentleGothicR">${place.getName() }</h1>
			   		 		
			   		 		<c:choose>
								<c:when test="${!place.isLike() }">
									<div class="btn heartBtn heartSize mt-1" data-heart-id="${placeId }"><i class="bi bi-suit-heart"></i></div>
									<div class="btn heartFillBtn none heartSize mt-1" data-heart-fill-id="${placeId }"><i class="bi bi-suit-heart-fill"></i></div>
								</c:when>
								
								<c:otherwise>
									<div class="btn heartBtn none heartSize mt-1" data-heart-id="${placeId }"><i class="bi bi-suit-heart"></i></div>
									<div class="btn heartFillBtn heartSize mt-1" data-heart-fill-id="${placeId }"><i class="bi bi-suit-heart-fill"></i></div>
								</c:otherwise>
							</c:choose>
   		 				
   		 			</div>
   		 			
   		 			<div class="mx-3">카테고리: <span id="menu">${place.getCategory() }</span></div>
   		 			
   		 			<div class="d-flex mx-2 mt-2">
   		 				<a id="showReview" class="mx-2 text-decoration-none text-primary" href="#"><i class="bi bi-list-stars"></i>&nbsp;리뷰(${reviewCount })</a>
   		 				<a class="mx-2 text-decoration-none text-danger" href="#"><i class="bi bi-suit-heart-fill"></i>&nbsp;좋아요(${likeCount })</a>
   		 			</div>
   		 			
   		 			<div class="my-3 mx-2">
						
						<div class="d-flex justify-content-between PuradakGentleGothicR">
							<h3 class="mx-2">위치</h3>
		   		 				<button type="button" id="reviewBtn" class="btn text-white shadow" data-toggle="modal" data-target="#exampleModal">
								  리뷰작성하기
								</button>
						</div>
   		 					
   		 					<div>
								<div id="googleMap" style="width: 500px;height: 300px;"></div>
   		 					</div>
   		 					
   		 			</div>
   		 			
   		 		</div>

   		 	
   		 	
   		 	</section>
   		 	
   		 	<c:import url="/WEB-INF/jsp/randomday/include/footer.jsp"></c:import>
		
		<!--  리뷰 작성 모달 -->
			<div class="modal fade PuradakGentleGothicR" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
			  <div class="modal-dialog" role="document">
			    <div class="modal-content">
			    
			      <div class="modal-header">
			      	<div id="placeId">${placeId }</div>
			        <h4 class="modal-title mx-3 my-2" id="exampleModalLabel">리뷰작성</h4>
			        <button type="button" class="close btn modalBtn" data-dismiss="modal" aria-label="Close">
			          <i class="bi bi-x" style="text-size:10px;"></i>
			        </button>
				  
			      </div>
			      <div class="modal-body">
			      
				     <!-- 별점 매기기 -->
				     <div class="d-flex mx-3">
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
					 
					<div class="d-flex">
						<textarea id="content" class="form-control mx-2"></textarea>
					</div>
			       	
			      </div>
			      
			      <div class="modal-footer">
			        <button type="button" id="saveReviewBtn" class="btn text-white shadow">리뷰 작성</button>
			        <button type="button" class="btn btn-secondary close shadow" data-dismiss="modal">닫기</button>
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
			        <h5 class="modal-title PuradakGentleGothicR mx-1" id="exampleModalLabel">${place.getName() }</h5>
			        <button type="button" class="close btn" data-dismiss="modal" aria-label="Close">
			          <i class="bi bi-x"></i>
			        </button>
			      </div>
			      
			      <div class="modal-body">
			      
			      	<table class="table PuradakGentleGothicR text-center align-middle">
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
					      			<td class="col-2">${review.getUserName() }</td>
					      			<td class="col-8">${review.getContent() }</td>
					      			<td class="col-2">${review.getPoint() }</td>
					      		</tr>
					       	
					       	</c:forEach>
			      		</tbody>
			      	</table>
			      </div>
			      
			    </div>
			  </div>
			</div>
	</div>			
<script>
/*
 *    function myMap(){
     var mapOptions = { 
             center:new google.maps.LatLng(51.508742, -0.120850),
             zoom:5
       };
  
       var map = new google.maps.Map( 
              document.getElementById("googleMap") 
             , mapOptions );
    }
 
 */

   
 var geocoder;
 var map;
 function myMap() {
   geocoder = new google.maps.Geocoder();
   var mapOptions = {
     zoom: 15,
   }
   map = new google.maps.Map(document.getElementById('googleMap'), mapOptions);
 }

 function codeAddress() {
	 // 주소를 입력받음
   var address = $("#roadAddress").text();
   geocoder.geocode( { 'address': address}, function(results, status) {
     if (status == 'OK') {
       map.setCenter(results[0].geometry.location);
       var marker = new google.maps.Marker({
           map: map,
           position: results[0].geometry.location
       });
     } else {
       alert("다음과 같은 이유로 지도가 생성되지 않았습니다 : " + status);
     }
   });
 }

</script> 
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyD1ZElYKU2D5SRSmZ_3cIi4VCN4tzHz8mo&callback=myMap"></script>
<script>
	
		$(document).ready(function(){
			
			// 시작과 동시에 장소 위치 지도에 표현
			codeAddress();
			
			// 평점 0점 세팅
			
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