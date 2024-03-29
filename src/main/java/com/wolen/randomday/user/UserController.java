package com.wolen.randomday.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wolen.randomday.domestic.bo.DomesticBO;
import com.wolen.randomday.domestic.like.bo.LikeBO;
import com.wolen.randomday.domestic.model.DetailPlace;

@Controller
@RequestMapping("/randomday")
public class UserController {

	@Autowired
	private LikeBO likeBO;
	
	@Autowired
	private DomesticBO domesticBO;
	
	
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
	
	// 유저 페이지 불러오기
	
	@RequestMapping("/user/userpage/view")
	public String userpage(HttpServletRequest request, Model model) {
		
		// 오른쪽 위에 프로필 표시를 위해 session에서 값 얻어오기
		
		HttpSession session = request.getSession();
		
		int userId = (Integer)session.getAttribute("userId");
		String userName = (String)session.getAttribute("userName");
		
		String imagePath = (String)session.getAttribute("imagePath");
		
		model.addAttribute("imagePath", imagePath);
		model.addAttribute("userName", userName);
		
		return "randomday/user/userpage";
	}
	
	
	// 좋아요 장소 목록 불러오기
	
	@RequestMapping("/user/likespaces/view")
	public String likeSpaces(HttpServletRequest request, Model model) throws JSONException, IOException {
		
		// 해당 유저가 좋아요 표시한 장소를 가져오기 위해 session에서 id얻어오기
		
		HttpSession session = request.getSession();
		
		int userId = (Integer)session.getAttribute("userId");
		String imagePath = (String)session.getAttribute("imagePath");
		
		// userId를 통해 like table에 있는 placeId 가져오기
		
		List<Integer> placeIdList = likeBO.getLikedPlaces(userId);
		
		// placeId들을 통해 장소들 가져오기
		List<DetailPlace> placeList = domesticBO.getDetailPlaces(placeIdList);

			
		model.addAttribute("imagePath", imagePath);
		model.addAttribute("placesId", placeIdList);
		model.addAttribute("places", placeList);

		
		
		return "randomday/user/likespaces";
	}
	
	
	// 회원정보 수정 페이지 불러오기
	@RequestMapping("/user/modify/view")
	public String modifyInfo(HttpServletRequest request
							, Model model) {
		
		HttpSession session = request.getSession();
		
		String imagePath = (String)session.getAttribute("imagePath");
		
		model.addAttribute("imagePath", imagePath);
		
		
		return "/randomday/user/modify";
		
	}
	
	
	// 로그아웃
	@GetMapping("/signout")
	public String signout(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		session.removeAttribute("userId");
		session.removeAttribute("userPass");
		session.removeAttribute("userName");
		session.removeAttribute("imagePath");
		
		return "redirect:/randomday/start/view";
		
	}
	
	
}
