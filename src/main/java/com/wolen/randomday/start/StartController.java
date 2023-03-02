package com.wolen.randomday.start;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/randomday")
public class StartController {

	// 시작페이지 불러오기
	
	@RequestMapping("/start/view")
	public String startMainPage() {
		return "randomday/start";
	}
	
	// 로그인페이지 불러오기
	
	@RequestMapping("/user/signin/view")
	public String signin() {
		return "randomday/user/signin";
	}
	
}
