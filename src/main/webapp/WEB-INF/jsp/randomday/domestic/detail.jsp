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
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyD1ZElYKU2D5SRSmZ_3cIi4VCN4tzHz8mo&callback=initMap&libraries=places" async defer></script>
<link rel="stylesheet" href="/static/css/style.css" type="text/css">
</head>
<body>
	
	<div id="wrap">
		
		
		<div id="map" style="width: 100%; height: 500px;"></div>
		
		
			<h1>장소 검색</h1>
		    <input type="text" id="search-input" placeholder="검색어를 입력하세요">
		    <button onclick="search()">검색</button>
		    <div id="places"></div>
		    <div id="place-photo-container">
		      <h2>선택한 장소의 사진</h2>
		      <img id="place-photo">
   		 	</div>

	</div>
<script>
		var map;
		var service;
		var markers = [];
		var centerLatLng;
		
		// 지도 불러오기
		
		function initMap() {
		    map = new google.maps.Map(document.getElementById("map"), {
		    	// 처음 맵을 불러 왔을 때 맵에 표시되어 있는 부분
		        center: { lat:  37.540734, lng: 127.076599 }, 
		        zoom: 15
		    });
		    
		    // 중심 좌표 저장
		    centerLatLng = map.getCenter();
		
		    // 장소 검색을 위한 서비스 생성
		    service = new google.maps.places.PlacesService(map);
		    
		}
		
		// 마커를 지우는 용도라는데 아직 정확한 사용방법은 모르겠음
		// ㄴ 아 함수를 선언해놓은거 구나
		
		function clearMarkers() {
		    for (var i = 0; i < markers.length; i++) {
		        markers[i].setMap(null);
		    }
		    markers = [];
		}
		
		function search() {
			
			// clearMarker가 작동되는거 같지가 않은데
		    clearMarkers();
			
			// html에 있는 장소들 다 지우기
		    $("#places").empty();
		
			// 키워드 입력받기
			
		    var keyword = $("#search-input").val();
			
			// 검색어 입력 안받았을 때
			
		    if (!keyword) {
		        alert("검색어를 입력하세요");
		        return;
		    }
			
			// service.nearbySearch() 함수는 검색에 사용되는 매개변수로 위치(position), 반경(radius), 검색 타입(type) 등을 받습니다.
			// 
			var request = {
					location: map.getCenter(),
				 	
				 	// 한국 내로 범위 한정
				  	bounds: {
					    south: 33.0,
					    west: 125.0,
					    north: 38.0,
					    east: 130.0
				  	},
					radius: "10000",
					type: "restaurant",
			        keyword: keyword					
					
				};
			
		
		    service.textSearch(request, function (results, status) {
		    	
		        if (status === google.maps.places.PlacesServiceStatus.OK) {
		            for (var i = 0; i < results.length; i++) {
		                var place = results[i];
		                var placeName = place.name;
		                var placeAddress = place.vicinity;
		                var $placeDiv = $("<div>")
		                    .text("Name: " + placeName + ", Address: " + placeAddress)
		                    .click(function () {
		                        var placeDetailsRequest = {
		                            placeId: place.place_id,
		                            fields: ["photos"],
		                        };
		                        service.getDetails(
		                            placeDetailsRequest,
		                            function (placeDetails, status) {
		                                if (status === google.maps.places.PlacesServiceStatus.OK) {
		                                    var photoUrl =
		                                        placeDetails.photos && placeDetails.photos[0]
		                                    
		                                    
		                                            ? placeDetails.photos[0].getUrl({ maxWidth: 500 })
		                                            : "";
		                                    $("#place-photo").attr("src", photoUrl);
		                                }
		                            }
		                        );
		                    });
		                $("#places").append($placeDiv);
		
		                var marker = new google.maps.Marker({
		                    position: place.geometry.location,
		                    map: map,
		                    title: placeName,
		                });
		
		                markers.push(marker);
		            }
		            var bounds = new google.maps.LatLngBounds();
		            for (var i = 0; i < markers.length; i++) {
		                bounds.extend(markers[i].getPosition());
		            }
		            map.fitBounds(bounds);
		        } else {
		            alert("검색 결과가 없습니다");
		        }
		    });
		}
</script>
</body>
</html>