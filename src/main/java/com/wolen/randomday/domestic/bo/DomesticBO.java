package com.wolen.randomday.domestic.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wolen.randomday.domestic.dao.DomesticDAO;
import com.wolen.randomday.domestic.model.DoAndSi;
import com.wolen.randomday.domestic.model.Gu;
import com.wolen.randomday.domestic.model.SearchResponse;

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
	
	// 검색 카테고리 기반으로 결과 출력
	
		@Autowired
		private RestTemplate restTemplate;
		


		public SearchResponse getResult(String searchKey, int display) throws JsonMappingException, JsonProcessingException {
		    String url = "https://openapi.naver.com/v1/search/local.json?query={query}&display={display}";

		    HttpHeaders headers = new HttpHeaders();
		    headers.set("X-Naver-Client-Id", "zEb6JKM7AaJpAVOVOoTF");
		    headers.set("X-Naver-Client-Secret", "7UH5uRIccL");

		    HttpEntity<String> entity = new HttpEntity<>(headers);

		    ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class, searchKey, display);

		    ObjectMapper objectMapper = new ObjectMapper();
		    SearchResponse result = objectMapper.readValue(response.getBody(), SearchResponse.class);

		    return result;
		}

}
