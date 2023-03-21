package com.wolen.randomday.start;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/randomday")
public class StartController {

	// 시작페이지 불러오기
	
	@RequestMapping("/start/view")
	public String startMainPage(
			HttpServletRequest request
			,Model model) {
		
		HttpSession session = request.getSession();
		
		String userName = (String)session.getAttribute("userName");
		String imagePath = (String)session.getAttribute("imagePath");
		
		model.addAttribute("imagePath", imagePath);
		model.addAttribute("userName", userName);
		
		
		return "randomday/start";
	}
	
}
