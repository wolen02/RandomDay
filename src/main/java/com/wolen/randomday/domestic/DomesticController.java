package com.wolen.randomday.domestic;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wolen.randomday.domestic.bo.DomesticBO;
import com.wolen.randomday.domestic.like.bo.LikeBO;
import com.wolen.randomday.domestic.model.DetailPlace;
import com.wolen.randomday.domestic.model.DoAndSi;
import com.wolen.randomday.domestic.model.Place;
import com.wolen.randomday.domestic.review.bo.ReviewBO;
import com.wolen.randomday.domestic.review.model.Review;

@Controller
@RequestMapping("/randomday/domestic")
public class DomesticController {
	
	@Autowired
	private DomesticBO domesticBO;	
	
	@Autowired
	private LikeBO likeBO;
	
	@Autowired
	private ReviewBO reviewBO;
	
	// 지역 선택 페이지
	
	@GetMapping("/select")
	public String select(Model model, HttpServletRequest request) {		

		List<DoAndSi> doList = domesticBO.getDoOrSi();
		
		HttpSession session = request.getSession();
		
		String imagePath = (String)session.getAttribute("imagePath");
		
		model.addAttribute("imagePath", imagePath);
		model.addAttribute("doList", doList);
		
		return"/randomday/domestic/select";
		

	}
	
	
	// 검색 결과 페이지
	
	@GetMapping("/result/view")
	public String result(
			Model model
			,@RequestParam("doName") String doName
			, @RequestParam("guName") String guName
			, HttpServletRequest request) throws IOException, JSONException {
		
		HttpSession session = request.getSession();
		
		int userId = (Integer)session.getAttribute("userId");
		String imagePath = (String)session.getAttribute("imagePath");
				
		List<Place> results = domesticBO.searchPlaces(userId, doName, guName);
		
		List<Place> resultsWithImage = domesticBO.getPlaceWithImage(results, doName, guName, userId);
		

		model.addAttribute("imagePath", imagePath);
		model.addAttribute("doName", doName);
		model.addAttribute("guName", guName);
		model.addAttribute("results", resultsWithImage);
		
		return "/randomday/domestic/result";
		
	}
	
	
	// 선택 장소 세부정보 페이지
	
	@GetMapping("/detail/view")
	public String detailSpace(
			HttpServletRequest request
			,Model model
			,@RequestParam("placeId") int placeId) throws IOException, JSONException {
		
		
		DetailPlace place = domesticBO.getDetailPlace(placeId);
		
		// session 에서 로그인 아이디를 통해 place의 isLike 좋아요 여부 확인
		
		HttpSession session = request.getSession();
		
		int userId = (Integer)session.getAttribute("userId");
		
		
		// 좋아요 여부 세팅
		
		boolean isLike = likeBO.isLike(userId, placeId);
		
		place.setLike(isLike);
		
		// 좋아요 개수 세팅
		
		int likeCount = likeBO.getLikeCountByplaceId(placeId);
		
		
		// 리뷰 세팅
		
		List<Review> reviews = reviewBO.getReviews(placeId);
		
		
		// 리뷰 개수 세팅
		
		int reviewCount = reviewBO.getReviewsCount(placeId);
		
		// 프로필 세팅
		
		String imagePath = (String)session.getAttribute("imagePath");		
		
		model.addAttribute("imagePath", imagePath);
		model.addAttribute("likeCount", likeCount);
		model.addAttribute("reviews", reviews);
		model.addAttribute("placeId", placeId);
		model.addAttribute("place", place);
		model.addAttribute("reviewCount", reviewCount);
		
		return "/randomday/domestic/detail";
		
	}
	
	
	
}
