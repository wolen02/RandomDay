package com.wolen.randomday.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wolen.randomday.user.bo.UserBO;
import com.wolen.randomday.user.model.User;

@RestController
@RequestMapping("/user")
public class UserRestController {

	@Autowired
	private UserBO userBO;
	
	// 로그인 api
	
	@PostMapping("/signin")
	public Map<String, String> signin(
			@RequestParam("loginId") String loginId
			, @RequestParam("password") String password) {
		
		
		Map<String, String> map = new HashMap<>();
		
		String result = userBO.getUser(loginId, password);
		
		if(result == null) {
			
			map.put("result", "failId");
			
		}else {
			
			if(result.equals("success")) {
				map.put("result", "success");
			}else {
				map.put("result","failPass");
			}
			
		}

		
		return map;
	}
	
	
	// 회원가입 api
	
	
	@PostMapping("/signup")
	public Map<String, String> signup(
			@RequestParam("loginId") String loginId
			, @RequestParam("password") String password
			, @RequestParam("name") String name
			, @RequestParam("birth") String birth
			, @RequestParam("sex") String sex
			, @RequestParam("email") String email){
		
		int count = userBO.addUser(loginId, password, name, birth, sex, email);
		
		Map<String, String> map = new HashMap<>();
		
		if(count == 1) {
			map.put("result", "success");
		}else {
			map.put("result", "fail");
		}
		
		return map;
		
	}
	
	
	// 중복확인 api
	
	@GetMapping("/duplicate_id")
	public boolean isDuplicate(@RequestParam("loginId") String loginId){
		
		return userBO.isduplicatedId(loginId);
		
		
	}
	
}
