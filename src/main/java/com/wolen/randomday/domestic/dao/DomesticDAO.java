package com.wolen.randomday.domestic.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.wolen.randomday.domestic.model.DoAndSi;
import com.wolen.randomday.domestic.model.Gu;
import com.wolen.randomday.domestic.model.MenuCategory;

@Repository
public interface DomesticDAO {

	
	// 도 또는 시 얻어오기
	public List<DoAndSi> selectDoOrSi();
	
	// 도 또는 시 선택
	public DoAndSi selectDoOrSiByName(@Param("name") String name);
	
	
	// 구 선택
	public List<Gu> selectGuList(@Param("doOrSiId") int doOrSiId);
	
	
	// 메뉴 종류 선택
	public MenuCategory selectMenu(@Param("randomNumber") int randomNumber);
	
	
	// 도 또는 시 이름을 통해 아이디 얻어오기
	public int selectDoOrSiIdByName(@Param("name") String name);
	
	
	// 구 이름을 통해 아이디 얻어오기
	public int selectGuIdByName(@Param("name") String name);
	
	
	// 도 아이디를 통해서 이름 얻어오기
	public DoAndSi selectDoOrSiNameById(@Param("doId") int doId);
	
	
	// 구 아이디를 통해서 이름 얻어오기
	public Gu selectGuNameById(@Param("guId") int guId);
	
	
	// 메뉴 아이디를 통해서 메뉴 이름 얻어괴
	public MenuCategory selectMenuNameById(@Param("menuId") int menuId);
	
	
}
