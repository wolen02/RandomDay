package com.wolen.randomday.domestic.pin.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wolen.randomday.domestic.pin.dao.PinDAO;
import com.wolen.randomday.domestic.pin.model.PinPlace;

@Service
public class PinBO {

	
	@Autowired
	private PinDAO pinDAO;
	
	
	// 장소 고정
	public int addpin(int userId, int placeId) {
	
		
		int placeIdLength = (int)( Math.log10(placeId)+1 );
		
		if(placeIdLength == 7) {
			
			String stringPlaceId = String.format("%02d", placeId);
			
			int menuId = Integer.parseInt(stringPlaceId.substring(4));
			
			return pinDAO.insertPinPlace(userId, menuId);
			
		}else {
			
		
			String stringPlaceId = String.format("%02d", placeId);
			
			int menuId = Integer.parseInt(stringPlaceId.substring(3));
			
			return pinDAO.insertPinPlace(userId, menuId);
			
		}
		
		
	}
	
	// 장소 고정 취소
	public int unpin(int userId, int placeId) {
		
		int placeIdLength = (int)( Math.log10(placeId)+1 );
		
		if(placeIdLength == 7) {
			
			String stringPlaceId = String.format("%02d", placeId);
			
			int menuId = Integer.parseInt(stringPlaceId.substring(4));
			
			return pinDAO.deletePinPlace(userId, menuId);
			
		}else {
			
		
			String stringPlaceId = String.format("%02d", placeId);
			
			int menuId = Integer.parseInt(stringPlaceId.substring(3));
			
			return pinDAO.deletePinPlace(userId, menuId);
			
		}
		
		
	}
	
	
	// 고정한 장소 얻어오기
	public List<PinPlace> getPlaces(int userId){
			
			return pinDAO.selectPlacesById(userId);

		
	}
	
	
	
}
