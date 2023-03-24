package com.wolen.randomday.domestic.pin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wolen.randomday.domestic.pin.bo.PinBO;

@RestController
public class RestPinController {
	
	
	@Autowired
	private PinBO pinBO;

	
	@GetMapping("/pin")
	public Map<String, String> pin(
			HttpServletRequest request
			,@RequestParam("placeId") int placeId
			, @RequestParam("doName") String doName
			, @RequestParam("guName") String guName
			, @RequestParam("menuName") String menuName
			, @RequestParam("imagePath") String imagePath
			, @RequestParam("title") String title){
		
		// 유저아이디와 장소 아이디를 받아 테이블에 저장
		
		HttpSession session = request.getSession();
		
		int userId = (Integer)session.getAttribute("userId");
		
		int count = pinBO.addpin(userId, placeId, doName, guName, menuName, imagePath, title);
		
		Map<String, String> map = new HashMap<>();
		
		if(count == 1) {
			map.put("result", "success");
		}else {
			map.put("result", "fail");
		}
		
		
		return map;
		
	}
	
	
	@GetMapping("/unPin")
	public Map<String, String> unPin(
			HttpServletRequest request
			,@RequestParam("placeId") int placeId){
		
		// 유저아이디와 장소 아이디를 받아 테이블에서 제거
		
				HttpSession session = request.getSession();
				
				int userId = (Integer)session.getAttribute("userId");
				
				int count = pinBO.unpin(userId, placeId);
				
				Map<String, String> map = new HashMap<>();
				
				if(count == 1) {
					map.put("result", "success");
				}else {
					map.put("result", "fail");
				}
				
				
				return map;
		
		
	}
	
	
	
}
