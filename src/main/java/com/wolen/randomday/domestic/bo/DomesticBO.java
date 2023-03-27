package com.wolen.randomday.domestic.bo;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import com.wolen.randomday.domestic.dao.DomesticDAO;
import com.wolen.randomday.domestic.like.bo.LikeBO;
import com.wolen.randomday.domestic.model.DetailPlace;
import com.wolen.randomday.domestic.model.DoAndSi;
import com.wolen.randomday.domestic.model.Gu;
import com.wolen.randomday.domestic.model.LikedPlace;
import com.wolen.randomday.domestic.model.MenuCategory;
import com.wolen.randomday.domestic.model.Place;
import com.wolen.randomday.domestic.pin.bo.PinBO;


@Service
public class DomesticBO {

	@Autowired
	private DomesticDAO domesticDAO;
	
	@Autowired
	private LikeBO likeBO;
	
	@Autowired
	private PinBO pinBO;
	
	
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

    	// 도아이디 문자열화
    	
    	String stringDoId = String.valueOf(domesticDAO.selectDoOrSiIdByName(doName));
    	
    	// 구 검색을 위한 도 아이디
    	int doId = Integer.parseInt(stringDoId);
    	
    	
    	// 구아이디 문자열화
    	
    	
    	int guId = domesticDAO.selectGuIdByName(doId, guName);
    	String stringGuId;
    	
    	if(guId <10) {
    		stringGuId = "00" + guId;
    	}else if(guId >=10 && guId < 100) {
    		stringGuId = "0" + guId;
    	}else {
    		stringGuId = String.valueOf(guId);
    	}
    	
    	
        List<Place> list = new ArrayList<>();
        

    	
    	for(int i = 1; i <= 15; i++) {
    		
    		
    		// map형태로 지역 정보 저장
    		Map<String, String> placeIdMap = new LinkedHashMap<>();
    		
    		placeIdMap.put(doName, stringDoId);
    		placeIdMap.put(guName, stringGuId);
    		
    		// 메뉴 아이디 문자열화
    		
    		String stringMenuId;
    		
    		if(i < 10) {
    			stringMenuId = "0" + i;
    		}else {
    			stringMenuId = String.valueOf(i);
    		}
    		
    		// 메뉴 이름 가져오기
    		
    		MenuCategory menuCategory = domesticDAO.selectMenu(i);
        	
        	String menuName = menuCategory.getName();
    		
        	placeIdMap.put(stringMenuId, menuName);
    		
    		
    		// 검색할 때마다 keyword 새로 작성
        	// 검색을 위한 키워드
        	String keyword = doName + " " + guName + " " + menuName; 
    		        	
    		
    		try {
    			keyword = URLEncoder.encode(keyword, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("검색어 인코딩 실패",e);
            }
        	
    		// 검색 url
            String apiURL = "https://openapi.naver.com/v1/search/local.json"
                    + "?query=" + keyword + "&display=10";
            		
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
                
                
                // 이 부분을 배열 가져온 배열의 길이 만큼 랜덤 숫자 출력하도록 수정하기
                int arrayLength = array.length();
                int randomNumber = (int)(Math.random()*arrayLength);
                
                // 장소아이디에 menuindex 추가
                
                String stringMenuIndex = String.valueOf(randomNumber);
                	
                placeIdMap.put("메뉴인덱스", stringMenuIndex);
                
                // placeId만들기
                
                String stringPlaceId = placeIdMap.get(doName) + placeIdMap.get(guName) + stringMenuId + placeIdMap.get("메뉴인덱스");
        		
        		
        		int placeId = Integer.parseInt(stringPlaceId);
                

                	
                    JSONObject object = array.getJSONObject(randomNumber);
                    String title = object.getString("title");
                    String address = object.getString("address");
                    String roadAddress = object.getString("roadAddress");
                    String telephone = object.getString("telephone");
                    double longitude = object.getDouble("mapx");
                    double latitude = object.getDouble("mapy");
                    String imageURL = null;

                    Place place = new Place(i, placeId, placeIdMap ,title, menuName, address, roadAddress, telephone, longitude, latitude, imageURL);
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
    		
    		// 메뉴 이름 얻어오기
    		String menuName = place.getCategory();
    		
    		
    		// 가게 이름과 지역을 통해 이미지 얻어오기
    		
    		String imageURL = searchPlaceImage(doName, guName, placeTitle);
    		
            if(imageURL == null) {
            	imageURL = "/static/images/"+ menuName + ".png";
            }
    		
    		place.setImageURL(imageURL);
    		
    		// 좋아요 했는지 안했는지 Session을 통해 얻어온 userId와 placeId를 통한 좋아요 여부 확인
    		
    		int placeId = place.getPlaceId();
    		
    		
    		boolean isLike = likeBO.isLike(userId, placeId);
    		
    		place.setLike(isLike);
    		
    		
    	}
    	
    	return places;
    	
    }
    
    
    
	 // 장소아이디를 통해 해당 장소 정보 가져오기
    
    public DetailPlace getDetailPlace(String doName, String guName, int placeId, String stringMenuName) throws IOException, JSONException {
    	
    	String stringPlaceId = String.valueOf(placeId);
    	int stringPlaceIdLength = stringPlaceId.length();
    	String menuName = stringMenuName;
    	// 키워드 설정
    	
    	String keyword = doName + " " + guName + " " + menuName;
    	
    		
    		try {
    			keyword = URLEncoder.encode(keyword, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("검색어 인코딩 실패",e);
            }
        	
    		// 검색 url
            String apiURL = "https://openapi.naver.com/v1/search/local.json"
                    + "?query=" + keyword  + "&display=10";
            		
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
                	
                
                // placeId 로부터 배열 index 얻기
            	int menuIndex = Integer.parseInt(stringPlaceId.substring(stringPlaceIdLength - 1));
                	
                    JSONObject object = array.getJSONObject(menuIndex);
                    String title = object.getString("title");
                    String category = object.getString("category");
                    String roadAddress = object.getString("roadAddress");
                    String telephone = object.getString("telephone");
                    String imageURL = searchPlaceImage(doName, guName, title); 
                   
                    DetailPlace detailPlace = new DetailPlace(title, category, roadAddress, telephone, null, 3, menuName, imageURL);
                    
                    return detailPlace;
                    
            } else { // 오류 발생
                System.out.println(responseCode);
                
                return null;
            }

    	
    }
    
    // 아아디에 따른 도 이름 얻기
    public String getDoNameById(int doId) {
    	
    	DoAndSi doOrSi = domesticDAO.selectDoOrSiNameById(doId);
    	
    	return doOrSi.getName();
    	
    }
    
    
    // 아아디에 따른 구 이름 얻기
    public String getGuNameById(int guId) {
    	
    	
    	Gu gu = domesticDAO.selectGuNameById(guId);
    	
    	return gu.getName();
    	
    }
    
    
    // 아이디에 따른 메뉴 이름 얻기
    public String getMenuNameById(int MenuId) {
    	
    	MenuCategory menu = domesticDAO.selectMenuNameById(MenuId);
    	
    	return menu.getName();
    	
    }
    
    
    // 좋아요 된 장소 아이디에 따른 장소들 얻기
    public List<DetailPlace> getDetailPlaces(List<Integer> placeIdList) throws IOException, JSONException {
    	
    	List<DetailPlace> placeList = new ArrayList<>();
    	
    	for(int placeId: placeIdList) {
    		
    			// placeTable에  placeId같은거 가져와서 keyword 조합
    		LikedPlace place = domesticDAO.selectPlaces(placeId);
    		
    		String doName = place.getDoName();
    		String guName = place.getGuName();
    		String menuName = place.getMenuName();
    		
    		String keyword = doName + " " + guName + " " + menuName;
    		
    		// placeId 에서 배열 index 추출
    		String stringPlaceId = String.valueOf(placeId);
    		int placeIdLength = stringPlaceId.length();

            	
        		
        		try {
        			keyword = URLEncoder.encode(keyword, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException("검색어 인코딩 실패",e);
                }
            	
        		// 검색 url
                String apiURL = "https://openapi.naver.com/v1/search/local.json"
                        + "?query=" + keyword  + "&display=5";
                		
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
                    if(array.length() == 0) {
                    	 DetailPlace detailPlace = new DetailPlace(null, null, null, null, null, 3, menuName, null);
                    	 placeList.add(detailPlace);
                    }else {
                    	 // placeId 로부터 배열 index 얻기
                    	int menuIndex = Integer.parseInt(stringPlaceId.substring(placeIdLength - 1));
                        	
                            JSONObject object = array.getJSONObject(menuIndex);
                            String title = object.getString("title");
                            String category = object.getString("category");
                            String roadAddress = object.getString("roadAddress");
                            String telephone = object.getString("telephone");
                            
                            String imageURL = searchPlaceImage(doName, guName, title);
                           
                            DetailPlace detailPlace = new DetailPlace(title, category, roadAddress, telephone, null, 3, menuName, imageURL);
                            
                            placeList.add(detailPlace);
                    }
                    
                    
                   
                        
                } else { // 오류 발생
                    System.out.println(responseCode);
                    
                    return placeList;
                    
                }
                
    	}
                
                return placeList;
                
    	
    	
    }

	
}

