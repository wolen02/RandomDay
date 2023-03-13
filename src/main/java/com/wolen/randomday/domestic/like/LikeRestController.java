package com.wolen.randomday.domestic.like;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wolen.randomday.domestic.like.bo.LikeBO;

@RestController
@RequestMapping("/place")
public class LikeRestController {
	
	
	@Autowired
	private LikeBO likeBO;
	
	
	// 좋아요 기능
	
	@GetMapping("/like")
	public Map<String, String> like(
			HttpServletRequest request
			, @RequestParam("placeId") int placeId){
		
		// javascript의 val()은 java에 전달되면 String 형태인가
		
		HttpSession session = request.getSession();
		
		int userId = (Integer)session.getAttribute("userId");
		
		int count = likeBO.like(userId, placeId);
		
		Map<String, String> map = new HashMap<>();
		
		if(count == 1) {
			map.put("result", "success");
		}else {
			map.put("result", "fail");
		}
		
		return map;
		
	}
	
	// 좋아요 취소 기능
	
	@GetMapping("/unLike")
	public Map<String, String> unLike(
			HttpServletRequest request
			, @RequestParam("placeId") int placeId){
		
		HttpSession session = request.getSession();
		

		int userId = (Integer)session.getAttribute("userId");
		
		int count = likeBO.unlike(userId, placeId);
		
		Map<String, String> map = new HashMap<>();
		
		if(count == 1) {
			map.put("result", "success");
		}else {
			map.put("result", "fail");
		}
		
		return map;
		
	}
		
	
}
