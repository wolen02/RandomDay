package com.wolen.randomday.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/randomday/user")
public class UserController {

	
	// 로그인 페이지 불러오기
	
	@RequestMapping("/signin/view")
	public String signin() {
		return "randomday/user/signin";
	}
	
	
	// 회원가입 페이지 불러오기
	
	@RequestMapping("/signup/view")
	public String signup() {
		return "randomday/user/signup";
	}
	
	
}
