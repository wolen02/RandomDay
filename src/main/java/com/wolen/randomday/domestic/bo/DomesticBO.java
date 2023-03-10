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
import com.wolen.randomday.domestic.model.DoAndSi;
import com.wolen.randomday.domestic.model.Gu;
import com.wolen.randomday.domestic.model.Place;


@Service
public class DomesticBO {

	@Autowired
	private DomesticDAO domesticDAO;
	
	
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
	
    public static List<Place> searchPlaces(String doName, String guName) throws IOException, JSONException {

		String keyword = doName + " " + guName + " 음식점";
		
		try {
			keyword = URLEncoder.encode(keyword, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패",e);
        }
    	
		// 검색 url
        String apiURL = "https://openapi.naver.com/v1/search/local.json"
                + "?query=" + keyword  + "&display=20&start=" + (int)(Math.random()*100); // 검색 결과 수
        		
        // 검색  url의 연결
        URL url = new URL(apiURL);
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("X-Naver-Client-Id", CLIENT_ID);
        con.setRequestProperty("X-Naver-Client-Secret", CLIENT_SECRET);
        

        List<Place> list = new ArrayList<>();

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

            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                String title = object.getString("title");
                String category = object.getString("category");
                String address = object.getString("address");
                String roadAddress = object.getString("roadAddress");
                String telephone = object.getString("telephone");
                double longitude = object.getDouble("mapx");
                double latitude = object.getDouble("mapy");
                String imageURL = null;

                Place place = new Place(title, category, address, roadAddress, telephone, longitude, latitude, imageURL);
                list.add(place);
            }

        } else { // 오류 발생
            System.out.println(responseCode);
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
    
    public List<Place> getPlaceWithImage(List<Place> places, String doName, String guName) throws IOException, JSONException{
    	
    	for(Place place:places) {
    		
    		// 가게 이름 얻어오기
    		String placeTitle = place.getTitle();
    		
    		
    		// 가게 이름과 지역을 통해 이미지 얻어오기
    		
    		String imageURL = searchPlaceImage(doName, guName, placeTitle);
    		
    		place.setImageUrl(imageURL);
    		
    		
    	}
    	
    	return places;
    	
    }

	
}

