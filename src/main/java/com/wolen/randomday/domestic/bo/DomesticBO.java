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
import com.wolen.randomday.domestic.model.DetailPlace;
import com.wolen.randomday.domestic.model.DoAndSi;
import com.wolen.randomday.domestic.model.Gu;
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
	
    public List<Place> searchPlaces(int userId, String doName, String guName) throws IOException, JSONException {

    	// 검색을 위한 키워드
    	String keyword = null;
    	
    	
    	// 장소를 구별하기 위한 장소아이디 만들기 (도아이디 + 구아이디+ 메뉴아이디 + 배열아이디)
    	
    	int doId = domesticDAO.selectDoOrSiIdByName(doName);
    	
    	    	
    	int guId = domesticDAO.selectGuIdByName(guName);
    	
    	
    	// 구 아이디 2자리수로 만들기
    	String stringGuId = String.format("%02d", guId);
    	
    	
        List<Place> list = new ArrayList<>();
        

    	
    	for(int i = 1; i <= 15; i++) {
    		
    		// 장소 검색할 때마다 장소아이디 초기화
    		
        	String stringPlaceId = String.format("%02d", doId);
        	
        	stringPlaceId += stringGuId;
        	

        	String stringMenuId = String.format("%02d", i);
        	stringPlaceId += stringMenuId;

        	// pin insert할때 BO에서 애초에 장소아이디를 메뉴아이디로 바꾸는 작업하고 저장한다음에
            // pin누르면 pin에서 오름차순으로 메뉴 아이디 가져온다음에 중복된거 있으면 stringplaceNumber에 추가
        	
        	
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
    
    
    
	 // 장소아이디를 통해 해당 장소 정보 가져오기
    
    public DetailPlace getDetailPlace(int placeId) throws IOException, JSONException {
    	
    	
    	// 받아온 장소아이디 길이 체크
    	int placeIdLength = (int)(Math.log10(placeId)+1);
    	
    	
    	// substring 하기 위해 placeId String화
    	String stringPlaceId = String.valueOf(placeId);
    	
    	
    	int menuId;
    	
    	
    	// 키워드 선언
    	String keyword = "";
   	
    	if(placeIdLength == 6) {
    		
    		// 도 아이디를 통해 도 이름 키워드에 추가
    		
    		int doId = placeId / 100000;
    		
    		keyword += getDoNameById(doId);
    		
    		// 구 아이디를 통해 구 이름 키워드에 추가
    		
    		int guId = Integer.parseInt(stringPlaceId.substring(1,3));
    		
    		String guName = getGuNameById(guId);
    		
    		keyword += guName;
    		
    		// 메뉴 아이디를 통해 메뉴 이름 추가
    		
    		menuId = Integer.parseInt(stringPlaceId.substring(3,5));
    		
    		keyword += getMenuNameById(menuId);
    		
    		
    	}else {
    		
    		// 도 아이디를 통해 도 이름 키워드에 추가
    		
    		int doId = placeId / 10000;
    		
    		keyword += getDoNameById(doId);
    		
    		// 구 아이디를 통해 구 이름 키워드에 추가
    		
    		int guId = Integer.parseInt(stringPlaceId.substring(2,4));
    		
    		String guName = getGuNameById(guId);
    		
    		keyword += guName;
    		
    		// 메뉴 아이디를 통해 메뉴 이름 추가
    		
    		menuId = Integer.parseInt(stringPlaceId.substring(4,6));
    		
    		keyword += getMenuNameById(menuId);
    		
    		
    	}

        	
    		
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
                	
                
                // placeId 로부터 배열 index 얻기
            	int menuIndex = Integer.parseInt(stringPlaceId.substring(placeIdLength - 1));
                	
                    JSONObject object = array.getJSONObject(menuIndex);
                    String title = object.getString("title");
                    String category = object.getString("category");
                    String menu = getMenuNameById(menuId);
                    String roadAddress = object.getString("roadAddress");
                    String telephone = object.getString("telephone");
                   
                    DetailPlace detailPlace = new DetailPlace(title, category, roadAddress, telephone, null, 3, menu);
                    
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
    
    
    // 장소 아이디에 따른 장소들 얻기
    public List<DetailPlace> getDetailPlaces(List<Integer> placeIdList) throws IOException, JSONException {
    	
    	List<DetailPlace> placeList = new ArrayList<>();
    	
    	for(int placeId: placeIdList) {
    		
    		// 받아온 장소아이디 길이 체크
        	int placeIdLength = (int)(Math.log10(placeId)+1);
        	
        	
        	// substring 하기 위해 placeId String화
        	String stringPlaceId = String.valueOf(placeId);
        	
        	
        	int menuId;
        	
        	
        	// 키워드 선언
        	String keyword = "";
       	
        	if(placeIdLength == 6) {
        		
        		// 도 아이디를 통해 도 이름 키워드에 추가
        		
        		int doId = placeId / 100000;
        		
        		keyword += getDoNameById(doId);
        		
        		// 구 아이디를 통해 구 이름 키워드에 추가
        		
        		int guId = Integer.parseInt(stringPlaceId.substring(1,3));
        		
        		String guName = getGuNameById(guId);
        		
        		keyword += guName;
        		
        		// 메뉴 아이디를 통해 메뉴 이름 추가
        		
        		menuId = Integer.parseInt(stringPlaceId.substring(3,5));
        		
        		keyword += getMenuNameById(menuId);
        		
        		
        	}else {
        		
        		// 도 아이디를 통해 도 이름 키워드에 추가
        		
        		int doId = placeId / 10000;
        		
        		keyword += getDoNameById(doId);
        		
        		// 구 아이디를 통해 구 이름 키워드에 추가
        		
        		int guId = Integer.parseInt(stringPlaceId.substring(2,4));
        		
        		String guName = getGuNameById(guId);
        		
        		keyword += guName;
        		
        		// 메뉴 아이디를 통해 메뉴 이름 추가
        		
        		menuId = Integer.parseInt(stringPlaceId.substring(4,6));
        		
        		keyword += getMenuNameById(menuId);
        		
        		
        	}

            	
        		
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
                    	
                    
                    // placeId 로부터 배열 index 얻기
                	int menuIndex = Integer.parseInt(stringPlaceId.substring(placeIdLength - 1));
                    	
                        JSONObject object = array.getJSONObject(menuIndex);
                        String title = object.getString("title");
                        String category = object.getString("category");
                        String menu = getMenuNameById(menuId);
                        String roadAddress = object.getString("roadAddress");
                        String telephone = object.getString("telephone");
                       
                        DetailPlace detailPlace = new DetailPlace(title, category, roadAddress, telephone, null, 3, menu);
                        
                        placeList.add(detailPlace);
                        
                } else { // 오류 발생
                    System.out.println(responseCode);
                    
                    return placeList;
                    
                }
                
    	}
                
                return placeList;
                
    	
    	
    }

	
}

