package com.wolen.randomday.domestic.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.wolen.randomday.domestic.model.DoAndSi;
import com.wolen.randomday.domestic.model.Gu;

@Repository
public interface DomesticDAO {

	
	// 도 또는 시 얻어오기
	public List<DoAndSi> selectDoOrSi();
	
	// 도 또는 시 선택
	public DoAndSi selectDoOrSiByName(@Param("name") String name);
	
	
	// 구 선택
	public List<Gu> selectGuList(@Param("doOrSiId") int doOrSiId);
	
}
