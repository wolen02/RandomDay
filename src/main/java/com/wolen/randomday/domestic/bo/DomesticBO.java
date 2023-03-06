package com.wolen.randomday.domestic.bo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wolen.randomday.domestic.dao.DomesticDAO;
import com.wolen.randomday.domestic.model.DoAndSi;
import com.wolen.randomday.domestic.model.Gu;
import com.wolen.randomday.domestic.model.Result;

@Service
public class DomesticBO {

	@Autowired
	private DomesticDAO domesticDAO;
	
	
	// 도 정보 얻기
	public List<DoAndSi> getDoOrSi(){
		
		return domesticDAO.selectDoOrSi();
		
	}
	
	// 도이름에 따른 구 정보들 얻기
	
	public List<String> getGuNameList(String name) {
		
		DoAndSi doOrSi = domesticDAO.selectDoOrSiByName(name);
		
		int doOrSiId = doOrSi.getId();
		
		List<Gu> guList = domesticDAO.selectGuList(doOrSiId);
		
		
		List<String> guNameList = new ArrayList<>();
		
		for(Gu gu:guList) {

			guNameList.add(gu.getName());
			
		}
		
		return guNameList;
		
	}
	
	
	// 검색 카테고리 기반으로 결과가 존재하는지 안하는지 여부
	
	public int isResultExist(
			String doName
			, String guName) {
		
		Result result = domesticDAO.selecResult(doName, guName);
		
		if(result != null) {
			return 1;
		}else {
			return 0;
		}

		
	}
	

	
	// 검색 카테고리 기반으로 결과 출력
	
	public Object getResult(
			String doName
			, String guName) throws IOException {

		
	}
	
	
	
	
	
}
