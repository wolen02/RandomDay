<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>지역선택</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
<link rel="stylesheet" href="/static/css/style.css" type="text/css">
</head>
<body>

	<div id="wrap">
		
		<c:import url="/WEB-INF/jsp/randomday/include/header.jsp" />
		
		
		<section class="d-flex">

		<div class="map-wrapper mt-4">
		
			<img src="/static/images/한국 전체 축소.png" usemap="#image-map">

			<map name="image-map">
			    <area class="area" alt="서울" coords="143,135,155,135,155,143,155,151,164,150,160,158,151,170,140,168,128,164,118,153" shape="poly">
			    <area class="area" alt="강원도" coords="432,288,438,277,440,267,433,253,434,228,424,210,418,193,412,182,407,175,397,162,388,145,391,138,351,93,344,81,327,44,324,34,320,27,312,7,304,6,303,17,288,39,276,46,255,44,249,46,239,42,227,42,219,42,206,46,196,43,185,44,170,44,160,49,151,56,146,60,155,67,158,76,169,74,171,67,180,71,185,68,185,77,190,81,200,78,202,94,211,94,215,101,223,102,220,111,210,117,212,126,208,133,214,134,210,144,227,152,238,154,244,162,236,167,214,211,241,207,242,213,253,211,262,200,267,208,291,202,298,208,293,215,308,215,313,222,324,223,340,228,350,231,354,221,365,222,384,225,399,225,398,231,390,233,389,239,395,246,400,253,405,255,413,256,412,266,410,278,412,285,419,286" shape="poly">
			    <area class="area" alt="경기도" coords="239,160,231,169,202,227,196,234,192,237,186,246,169,249,161,243,153,243,143,245,130,242,118,232,113,225,119,212,115,206,106,214,103,205,109,194,117,194,117,184,110,183,115,173,117,164,115,153,106,150,95,146,90,138,88,122,103,119,106,105,107,96,114,92,120,92,135,67,140,59,154,75,162,75,170,71,178,69,183,77,198,79,198,90,206,94,216,100,209,112,207,127,203,135,208,141,212,146,231,155" shape="poly">
			    <area class="area" alt="인천" coords="114,169,112,155,94,147,76,118,49,117,74,196,96,208" shape="poly">
			    <area class="area" alt="충청북도" coords="213,366,230,375,250,371,257,354,265,349,260,339,249,339,241,330,244,300,233,292,245,278,255,274,264,275,265,264,286,256,298,263,310,259,310,244,335,230,317,222,307,215,293,218,295,207,279,206,263,205,252,213,237,213,226,211,216,212,208,219,204,228,195,238,188,246,176,250,182,264,177,276,173,284,177,293,185,298,185,306,194,312,202,311,203,320,202,333,202,343,209,351" shape="poly">
			    <area class="area" alt="대전" coords="183,307,177,330,180,341,186,338,193,343,200,340,200,329,200,317" shape="poly">
			    <area class="area" alt="충청남도" coords="156,364,178,359,199,376,213,374,209,361,200,348,190,348,178,344,174,335,175,322,179,311,180,303,172,292,169,278,177,266,173,253,161,247,147,248,130,248,124,253,113,238,101,237,87,227,59,239,44,254,40,268,58,278,60,288,65,306,72,319,89,336,88,349,98,362,107,370,121,367,131,359,145,354" shape="poly">
			    <area class="area" alt="전라북도" coords="87,636,105,637,137,632,155,607,181,592,200,595,210,594,237,585,241,565,235,538,235,521,234,511,226,500,220,492,216,483,214,473,204,469,195,475,168,474,157,473,149,460,137,461,130,455,120,460,109,469,95,471,89,464,78,463,72,468,65,477,31,502,21,533,12,550,5,567,25,562,30,577,56,573,36,593,27,607,26,618,43,616,64,598,75,581,78,594,79,602,85,606" shape="poly">
			    <area class="area" alt="광주" coords="133,478,117,480,108,492,110,500,124,507,142,501,146,492" shape="poly">
			    <area class="area" alt="경상북도" coords="391,226,387,243,401,255,406,273,408,285,427,286,424,307,420,348,428,372,443,360,430,409,424,423,392,416,370,426,349,435,326,432,325,414,341,407,349,385,343,370,321,374,318,386,309,386,303,399,302,412,305,427,292,424,283,416,274,404,257,394,249,373,265,346,257,333,242,327,247,302,236,289,253,278,273,277,273,265,301,264,315,260,317,248,332,235,346,232,358,226" shape="poly">
			    <area class="area" alt="대구" coords="305,430,307,405,309,388,319,387,322,375,345,371,347,382,342,395,339,406,326,413,322,424,316,428" shape="poly">
			    <area class="area" alt="울산" coords="429,429,411,426,398,420,385,424,376,435,389,447,403,459,412,466,421,464,418,453,426,447,432,437" shape="poly">
			    <area class="area" alt="부산" coords="398,465,386,473,376,480,366,490,361,498,375,498,375,507,388,495,401,491,409,470" shape="poly">
			    <area class="area" alt="경상남도" coords="246,395,269,406,283,423,297,431,320,433,342,440,368,436,384,452,399,463,376,477,356,497,355,511,347,539,334,552,315,545,302,541,294,564,287,564,283,539,265,543,266,550,253,552,242,546,238,531,238,513,232,503,219,480,221,463,221,444,220,427,232,407" shape="poly">
			    <area class="area" alt="제주도" coords="121,677,136,693,120,714,96,718,71,723,53,724,44,709,57,692,91,680" shape="poly">
			</map>
			
			
			<div class="popup" id="서울Popup">
				<img  src="/static/images/서울 특별시.png">
			</div>
			
			<div class="popup" id="강원Popup">
				<img src="/static/images/강원도.png">
			</div>
			
			<div class="popup" id="경기Popup">
				<img src="/static/images/경기도.png">
			</div>
			
			<div class="popup" id="경남Popup">
				<img src="/static/images/경상남도.png">
			</div>
			
			<div class="popup" id="경북Popup">
				<img src="/static/images/경상북도.png">
			</div>
			
			<div class="popup" id="광주Popup">
				<img src="/static/images/광주 광역시.png">
			</div>
			
			<div class="popup" id="대구Popup">
				<img src="/static/images/대구 광역시.png">
			</div>
			
			<div class="popup" id="대전Popup">
				<img src="/static/images/대전 광역시.png">
			</div>
			
			<div class="popup" id="부산Popup">
				<img src="/static/images/부산 광역시.png">
			</div>
			
			<div class="popup" id="울산Popup">
				<img src="/static/images/울산 광역시.png">
			</div>
			
			<div class="popup" id="인천Popup">
				<img src="/static/images/인천 광역시.png">
			</div>
			
			<div class="popup" id="전남Popup">
				<img src="/static/images/전라남도.png">
			</div>
			
			<div class="popup" id="전북Popup">
				<img src="/static/images/전라북도.png">
			</div>
			
			<div class="popup" id="제주Popup">
				<img src="/static/images/제주특별자치도.png">
			</div>
			
			<div class="popup" id="충남Popup">
				<img src="/static/images/충청남도.png">
			</div>
			
			<div class="popup" id="충북Popup">
				<img src="/static/images/충청북도.png">
			</div>

			<div class="popup" id="전국Popup">
				<img src="/static/images/한국 전체2.png">
			</div>
			
		</div>

			

			<div class="mx-4">
				
				<div class="mx-1 my-3">
					<h1 class="text-success"><b>지역선택</b></h1>
				</div>
				
				<div>
					<ul class="nav nav-fill text-white">
					
						<c:forEach var="dodo" items="${doList }">
							<li id="${dodo.name }" class="btn selectBtn shadow"><b>${dodo.name }</b></li>								
						</c:forEach>
						
					</ul>
				</div>
				
				
				<div id="detailArea" class="mt-3">
					<h3 class="text-success my-2"><b>세부지역 선택</b></h3>
					
					<div class="d-flex justify-content-around">
						<h1 id="doName" class="my-3"><b></b></h1>
						<select id="guSelect" class="form-select mt-3"></select>
					</div>

				</div>
				
				<div class="d-flex justify-content-end align-items-end mt-5">
					<button id="searchBtn" class="btn text-white shadow">검색</button>
				</div>

			</div>
			
				
						
		</section>


		
		<c:import url="/WEB-INF/jsp/randomday/include/footer.jsp" />
		
	</div>
	
<script>

	$(document).ready(function(){
		
		
		var popupId;
		var doName;
		var guName;
		
		// 검색 버튼 누를 때 결과페이지로 넘어가기
		$("#searchBtn").on("click", function(){
			
			if(doName == "전국"){
				alert("전국 서비스는 준비중입니다.");
				return;
			}
			
			location.href="/randomday/domestic/result/view?doName=" + doName + "&&guName=" +guName;
			
			});		
			
		
		
		// 구 선택 변경 할떄마다 guName에 추가
		$("#guSelect").change(function(){
			
			guName = $("#guSelect").val();
			
		});
		
		
		
		// 지역 버튼 클릭시 해당 영역 색변화
		$(".selectBtn").on("click", function() {
				
				doName = $(this).attr("id");
				$("#searchBtn").fadeIn("500");
			
				if(popupId == null){
					popupId = "#" + doName + "Popup";
				      $(popupId).fadeIn("800");
				      
				      
				      // 도 선택에 따른 도이름과 세부지역 카테고리 표시
				      
				      $.ajax({
				    	  type:"get"
				    	  , url:"/space/category_add"
				    	  , data:{"name":doName}
				      	 , success:function(data){
				 			
				      		 // 옵션들을 다 삭제한 후 받아온 구 리스트들을 하나 씩 저장후 option에 추가
				      		 
				      		 let guList = data;
				      		 
				      		 $("#guSelect").empty();
				      		 
				      		 $.each(guList, function(index, gu){
				      			
				      			 // 사용자가 이미 선택되어 있는 상태를 보고 선택 안할 수도 있으니까 미리 값 넣어두기
				      			 if(index == 0){
				      				 guName = gu;
				      			 }
				      			 
				      			 $("#guSelect").append($("<option>").attr("value", gu).text(gu));
				      		 });	      		 
				      			 
				      		// 지역 선택에 따라 해당 지역 이름 보여주기
					      	
				      		$("#doName").text(doName);
				      		
				      		$("#detailArea").fadeIn("500");
				      		 
				      	 }
				      	 , error:function(){
				      		 alert("세부지역 카테고리 추가 오류");
				      		 return;
				      	 }
				      });
				      
				    }else{
					      $(popupId).hide();
					      popupId = null;
					      popupId = "#" + $(this).attr("id") + "Popup";
					      $(popupId).fadeIn("800");
					      
					      // 도 선택에 따른 도이름과 세부지역 카테고리 표시
					      
					      $.ajax({
					    	  type:"get"
					    	  , url:"/space/category_add"
					    	  , data:{"name":doName}
					      	 , success:function(data){
					 			
					      		 // 옵션들을 다 삭제한 후 받아온 구 리스트들을 하나 씩 저장후 option에 추가
					      		 
					      		 let guList = data;
					      		 
					      		 $("#guSelect").empty();
					      		 
					      		 $.each(guList, function(index, gu){
					      			 
					      			 // 사용자가 이미 선택되어 있는 상태를 보고 선택 안할 수도 있으니까 미리 값 넣어두기
					      			 if(index == 0){
					      				 guName = gu;
					      			 }
					      			 
					      			 $("#guSelect").append($("<option>").attr("value", gu).text(gu));
					      		 });				      		 
					      		 
							     // 버튼을 다시 누를 때 지역 이름이 바뀌기 위해 없애기
							 	$("#doName").text("");
					      			 
					      		// 지역 선택에 따라 해당 지역 이름 보여주기
						      	
					      		$("#doName").text(doName);
					      		
					      		$("#detailArea").fadeIn("500");
					      		 
					      	 }
					      	 , error:function(){
					      		 alert("세부지역 카테고리 추가 오류");
					      		 return;
					      	 }
					      });
					}
		
				});

		
			});
			
</script>
</body>
</html>