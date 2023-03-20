package com.wolen.randomday.domestic.review.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.wolen.randomday.domestic.review.model.Review;

@Repository
public interface ReviewDAO {
	
	// 리뷰작성
	
	public int insertReview(@Param("userId") int userId
			,@Param("userName") String userName
			,@Param("placeId") int placeId
			,@Param("content") String content);
	
	
	// 해당 장소 리뷰들 불러오기
	
	public List<Review> selectReviews(@Param("placeId") int placeId);
	
	// 해당 장소 리뷰개수 불러오기
	
	public int selectReviewCount(@Param("placeId") int placeId);
	
}
