package com.wolen.randomday.domestic.bo;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import com.wolen.randomday.domestic.dao.DomesticDAO;
import com.wolen.randomday.domestic.like.bo.LikeBO;
import com.wolen.randomday.domestic.model.DoAndSi;
import com.wolen.randomday.domestic.model.Gu;
import com.wolen.randomday.domestic.model.MenuCategory;
import com.wolen.randomday.domestic.model.Place;


@Service
public class DomesticBO {

	@Autowired
	private DomesticDAO domesticDAO;
	
	@Autowired
	private LikeBO likeBO;
	
	
	// 도 정보 얻기
	public List<DoAndSi> getDoOrSi(){
		
		return domesticDAO.selectDoOrSi();
		
	}
	
	// 도이름에 따른 구 정보들 얻기
	
	public List<String> getGuNameList(String name) {
		
		DoAndSi doOrSi = domesticDAO.selectDoOrSiByName(name);
		
		int doOrSiId = doOrSi.getId();
		
		List<Gu> guList = domesticDAO.selectGuList(doOrSiId);
		
		
		List<String> guNameList = new ArrayList<>();
		
		for(Gu gu:guList) {

			guNameList.add(gu.getName());
			
		}
		
		return guNameList;
		
	}
	
	
	
	// 장소 검색
	
    private static final String CLIENT_ID = "zEb6JKM7AaJpAVOVOoTF";
    private static final String CLIENT_SECRET = "7UH5uRIccL";
	
    public List<Place> searchPlaces(String doName, String guName) throws IOException, JSONException {

    	// 검색을 위한 키워드
    	String keyword = null;
    	
    	
    	// 장소를 구별하기 위한 장소아이디 만들기 (도아이디 + 구아이디+ 메뉴아이디 + 배열아이디)
    	
    	int doId = domesticDAO.selectDoOrSiIdByName(doName);
    	
    	int guId = domesticDAO.selectGuIdByName(guName);
    	
    	
        List<Place> list = new ArrayList<>();
    	
    	for(int i = 1; i <= 15; i++) {
    		
    		// 장소 검색할 때마다 장소아이디 초기화
    		
        	String stringPlaceId = String.valueOf(doId);
        	
        	stringPlaceId += String.valueOf(guId);
        	
        	// 장소를 구별하기 위한 장소 아이디에 메뉴아이디 추가
    		stringPlaceId += String.valueOf(i);
    		
        	
        	
    		// 검색 결과의 다양성을 위한 여러 키워드 가져오기
        	MenuCategory menuCategory = domesticDAO.selectMenu(i);
        	
        	String menuName = menuCategory.getName();
        	
        	keyword = doName + " " + guName + " " + menuName;
        	
        	
    		
    		try {
    			keyword = URLEncoder.encode(keyword, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("검색어 인코딩 실패",e);
            }
        	
    		// 검색 url
            String apiURL = "https://openapi.naver.com/v1/search/local.json"
                    + "?query=" + keyword  + "&display=5&start=1"; // 검색 결과 수
            		
            // 검색  url의 연결
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", CLIENT_ID);
            con.setRequestProperty("X-Naver-Client-Secret", CLIENT_SECRET);

            // 검색을 통한 장소 정보 얻기
            
            int responseCode = con.getResponseCode();
            if (responseCode == 200) { // 정상 호출
                InputStream is = con.getInputStream();
                byte[] buffer = new byte[1024];
                int readBytes = -1;
                StringBuffer response = new StringBuffer();
                while ((readBytes = is.read(buffer)) != -1) {
                    response.append(new String(buffer, 0, readBytes));
                }
                is.close();

                JSONObject json = new JSONObject(response.toString());
                JSONArray array = json.getJSONArray("items");
                
                int randomNumber = (int)(Math.random()*5);
                
                
                	// 장소 아이디에 배열 index 추가
                	stringPlaceId += String.valueOf(randomNumber);
                	
                	// 장소아이디 정수형으로 변경
                	int placeId = Integer.parseInt(stringPlaceId);
                	
                	
                    JSONObject object = array.getJSONObject(randomNumber);
                    String title = object.getString("title");
                    String category = object.getString("category");
                    String address = object.getString("address");
                    String roadAddress = object.getString("roadAddress");
                    String telephone = object.getString("telephone");
                    double longitude = object.getDouble("mapx");
                    double latitude = object.getDouble("mapy");
                    String imageURL = null;

                    Place place = new Place(i, placeId ,title, category, address, roadAddress, telephone, longitude, latitude, imageURL);
                    list.add(place);

            } else { // 오류 발생
                System.out.println(responseCode);
            }
    		

    	}
    	
    			return list;
    			
    	}
    
    
    
	    // 장소 주소와 이름을 받아 해당 장소 검색
	    
	    public String searchPlaceImage(String doName, String guName, String title) throws IOException, JSONException {
	    	
	    	String imageKeyword = doName + guName + title;
	    	
	    	try {
	    		imageKeyword = URLEncoder.encode(imageKeyword, "UTF-8");
	        } catch (UnsupportedEncodingException e) {
	            throw new RuntimeException("검색어 인코딩 실패",e);
	        }
	        
	        
	        // 이미지 url
	        String imageApiURL = "https://openapi.naver.com/v1/search/image?query=" + imageKeyword  + "&display=10&start=1&sort=sim";
	
	        
	        // 이미지 url의 연결
	        URL url = new URL(imageApiURL);
	        HttpURLConnection con = (HttpURLConnection)url.openConnection();
	        con.setRequestMethod("GET");
	        con.setRequestProperty("X-Naver-Client-Id", CLIENT_ID);
	        con.setRequestProperty("X-Naver-Client-Secret", CLIENT_SECRET);
	    	
	    	
		    int responseCode = con.getResponseCode();
		    if (responseCode == 200) { // 정상 호출
		        InputStream is = con.getInputStream();
		        byte[] buffer = new byte[1024];
		        int readBytes = -1;
		        StringBuffer response = new StringBuffer();
		        while ((readBytes = is.read(buffer)) != -1) {
		            response.append(new String(buffer, 0, readBytes));
		        }
		        is.close();
		
		        JSONObject json = new JSONObject(response.toString());
		        JSONArray array = json.getJSONArray("items");
		
		        for (int i = 0; i < array.length(); i++) {
		            JSONObject object = array.getJSONObject(i);
		            String imageURL = object.getString("link");
		            
		            
		    		try {
		    			imageKeyword = URLEncoder.encode(imageKeyword, "UTF-8");
		            } catch (UnsupportedEncodingException e) {
		                throw new RuntimeException("검색어 인코딩 실패",e);
		            }
		            
		    		return imageURL;
		    		
		        }
		
		    } else { // 오류 발생
		        System.out.println(responseCode);
		    }
		    
		    return null;
		    
		}
    
    
    // 검색 장소와 이미지 일치 시키기
    
    public List<Place> getPlaceWithImage(
    		List<Place> places
    		, String doName
    		, String guName
    		, int userId) throws IOException, JSONException{
    	
    	for(Place place:places) {
    		
    		
    		// 가게 이름 얻어오기
    		String placeTitle = place.getTitle();
    		
    		
    		// 가게 이름과 지역을 통해 이미지 얻어오기
    		
    		String imageURL = searchPlaceImage(doName, guName, placeTitle);
    		
    		
    		place.setImageUrl(imageURL);
    		
    		// 좋아요 했는지 안했는지 Session을 통해 얻어온 userId와 placeId를 통한 좋아요 여부 확인
    		
    		int placeId = place.getPlaceId();
    		
    		
    		boolean isLike = likeBO.isLike(userId, placeId);
    		
    		place.setLike(isLike);
    		
    		
    	}
    	
    	return places;
    	
    }

	
}

