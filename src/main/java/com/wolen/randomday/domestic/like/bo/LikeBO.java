package com.wolen.randomday.domestic.like.bo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Service;

import com.wolen.randomday.domestic.bo.DomesticBO;
import com.wolen.randomday.domestic.like.dao.LikeDAO;
import com.wolen.randomday.domestic.model.DetailPlace;

@Service
public class LikeBO {

	@Autowired
	private LikeDAO likeDAO;
	
	
	
	
	// 좋아요 기능
	
	public int like(int userId, int placeId) {
		
		return likeDAO.like(userId, placeId);
		
	}
	
	
	// 좋아요 취소 기능
	
	public int unLike(int userId, int placeId) {
		
		return likeDAO.unLike(userId, placeId);
		
	}
	
	
	// 좋아요 중복확인 기능
	
	public Boolean isLike(int userId, int placeId) {
		
		int count = likeDAO.isLike(userId, placeId);
		
		
		if(count == 0) {
			return false;
		}else {
			return true;
		}
		
		
	}
	
	// 좋아요 누른 장소아이디 가져오기
	
	public List<Integer> getLikedPlaces(int userId) throws IOException, JSONException{
		
		
		List<Integer> list = likeDAO.selectLikedPlace(userId);
		
		return list;
		
		
	}
	
	// 해당 장소 좋아요 개수 가져오기
	
	public int getLikeCountByplaceId(int placeId) {
		
		return likeDAO.selectLikeCountByPlaceId(placeId);
		
	}
	
	
	
}