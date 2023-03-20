package com.wolen.randomday.domestic.review;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wolen.randomday.domestic.review.bo.ReviewBO;

@RestController
public class ReviewRestController {
	
	@Autowired
	private ReviewBO reviewBO;

	
	// 리뷰작성
	@PostMapping("/add/review")
	public Map<String, String> addReview(
			HttpServletRequest request
			,double point, int placeId, String content){
		
		HttpSession session = request.getSession();
		
		int userId = (Integer)session.getAttribute("userId");
		
		String userName = (String)session.getAttribute("userName");
		
		int count = reviewBO.addReview(userId, userName, point, placeId, content);
		
		Map<String, String> map = new HashMap<>();
		
		if(count == 1) {
			map.put("result", "success");
		}else {
			map.put("result", "fail");
		}
		
		
		return map;
	}
	
	
}
