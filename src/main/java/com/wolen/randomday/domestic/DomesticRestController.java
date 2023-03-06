package com.wolen.randomday.domestic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wolen.randomday.domestic.bo.DomesticBO;

@RestController
public class DomesticRestController {
	
	@Autowired
	private DomesticBO domesticBO;

	// 선택한 도 버튼으로 구 카테고리선택창이 설정됨
	
	@GetMapping("/space/category_add")
	public List<String> getCategory(@RequestParam("name") String name){

		return domesticBO.getGuNameList(name);

		
	}
	
	
	// 선택한 카테고리 기반으로 결과 검색
	
	@GetMapping("/search/space")
	public Map<String, String> getResult(
			@RequestParam("doName") String doName
			, @RequestParam("guName") String guName){
		
		Map<String, String> map = new HashMap<>();
		
		int count = domesticBO.isResultExist(doName, guName);
		
		if(count == 1) {
			map.put("result", "success");
		}else {
			map.put("result", "fail");
		}
		
		return map;
		
	}
	
	
}
