package com.wolen.randomday.domestic.pin.bo;

import java.util.ArrayList;
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
	public int addpin(int userId
			, int placeId
			, String doName
			, String guName
			, String menuName
			, String imagePath
			, String title) {
		
		return pinDAO.insertPinPlace(userId, placeId, doName, guName, menuName, imagePath, title);
		
	}
	
	// 장소 고정 취소
	public int unpin(int userId, int placeId) {
		
		return pinDAO.deletePinPlace(userId, placeId);
		
	}
	
	
	// 고정한 장소 얻어오기
	public List<PinPlace> getPlaces(int userId){
			
		List<PinPlace> pinPlaces = new ArrayList<>();
		
		
		for(PinPlace place:pinDAO.selectPlacesById(userId)) {
			place.setPin(true);
			pinPlaces.add(place);
		}
		
			return pinPlaces;

		
	}
	
	
	
	
	
}
