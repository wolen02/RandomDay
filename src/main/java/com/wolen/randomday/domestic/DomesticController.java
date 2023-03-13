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
import com.wolen.randomday.domestic.model.DoAndSi;
import com.wolen.randomday.domestic.model.Place;

@Controller
@RequestMapping("/randomday/domestic")
public class DomesticController {
	
	@Autowired
	private DomesticBO domesticBO;	
	
	// 지역 선택 페이지
	
	@GetMapping("/select")
	public String select(Model model) {		

		List<DoAndSi> doList = domesticBO.getDoOrSi();
		
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
				
		List<Place> results = domesticBO.searchPlaces(doName, guName);
		
		List<Place> resultsWithImage = domesticBO.getPlaceWithImage(results, doName, guName, userId);
		
		
		
		model.addAttribute("doName", doName);
		model.addAttribute("guName", guName);
		model.addAttribute("results", resultsWithImage);
		
		return "/randomday/domestic/result";
		
	}
	
	
	// 선택 장소 세부정보 페이지
	
	@GetMapping("/detail/view")
	public String detailSpace() {
		
		return "/randomday/domestic/detail";
		
	}
	
	
	
}
