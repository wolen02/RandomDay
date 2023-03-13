package com.wolen.randomday.domestic.like.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wolen.randomday.domestic.like.dao.LikeDAO;

@Service
public class LikeBO {

	@Autowired
	private LikeDAO likeDAO;
	
	
	
	// 좋아요 기능
	
	public int like(int userId, int placeId) {
		
		return likeDAO.like(userId, placeId);
		
	}
	
	
	// 좋아요 취소 기능
	
	public int unlike(int userId, int placeId) {
		
		return likeDAO.like(userId, placeId);
		
	}
	
	
	// 좋아요 중복확인 기능
	
	public boolean isLike(int userId, int placeId) {
		
		int count = likeDAO.isLike(userId, placeId);
		
		
		if(count == 0) {
			return false;
		}else {
			return true;
		}
		
		
	}
	
	
	
}