package com.wolen.randomday.domestic.review.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wolen.randomday.domestic.review.dao.ReviewDAO;
import com.wolen.randomday.domestic.review.model.Review;

@Service
public class ReviewBO {

	@Autowired
	private ReviewDAO reviewDAO;
	
	// 리뷰작성
	
	public int addReview(
			int userId
			, String userName
			, double point
			, int placeId
			, String content
			) {
		
		return reviewDAO.insertReview(userId, userName, point, placeId, content);
		
	}
	
	// placeId 기반으로 리뷰들 얻어오기
	
	public List<Review> getReviews(int placeId){
		
		return reviewDAO.selectReviews(placeId);
		
	}
	
	// placeId 기반으로 리뷰개수 얻어오기
	
	public int getReviewsCount(int placeId) {
		
		return reviewDAO.selectReviewCount(placeId);
		
	}
	
}
