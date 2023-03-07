package com.wolen.randomday.domestic;

import java.io.IOException;
import java.util.List;

import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wolen.randomday.domestic.bo.DomesticBO;
import com.wolen.randomday.domestic.model.DoAndSi;
import com.wolen.randomday.domestic.model.SearchResponse;

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
			, @RequestParam("guName") String guName) throws IOException {
		
		String searchKey = doName + guName + "맛집";
		
		SearchResponse results = domesticBO.getResult(searchKey, 10);
		
		model.addAttribute("doName", doName);
		model.addAttribute("guName", guName);
		model.addAttribute("results", results);
		
		return "/randomday/domestic/result";
		
	}
	
}
