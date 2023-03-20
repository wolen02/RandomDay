package com.wolen.randomday.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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
			, @RequestParam("password") String password
			, HttpServletRequest request) {		
		
		User user = userBO.getUser(loginId);
		
		Map<String, String> map = new HashMap<>();
		
		
		if(user == null) {
			
			map.put("result", "failId");
			
		}else {
			
			String userPassword = user.getPassword();
			
			// 입력한 비밀번호가 저장된 회원 비밀번호와 같을 때
			if(userPassword.equals(password)) {
				
				HttpSession session = request.getSession();
				
				int userId = user.getId();
				String userPass = user.getPassword();
				String userName = user.getName();
				
				session.setAttribute("userId", userId);
				session.setAttribute("userPass", userPass);
				session.setAttribute("userName", userName);
				
				map.put("result", "success");
			}else {
				map.put("result", "failPass");
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
	public Map<String, String> isDuplicate(@RequestParam("loginId") String loginId){
		
		Map<String, String> map = new HashMap<>();
		
		boolean result = userBO.isduplicatedId(loginId);
		
		if(!result) {
			map.put("result", "success");
		}else {
			map.put("result", "fail");
		}
		
		return map;
		
		
	}
	
	
	// 비밀번호 변경
	
	@PostMapping("/modify/password")
	public Map<String, String> modifyPass(
			@RequestParam("confirmPassword") String confirmPassword
			,@RequestParam("newPassword") String newPassword
			, HttpServletRequest request){
		
		Map<String, String> map = new HashMap<>();
		
		
		HttpSession session = request.getSession();
		
		int userId = (Integer)session.getAttribute("userId");
		
		int count = userBO.modifyPass(userId, confirmPassword, newPassword);
		
		if(count == 1) {
			map.put("result", "success");
		}else {
			map.put("result", "fail");
		}
		
		
		return map;
		
	}
	
}
