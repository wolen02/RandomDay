package com.wolen.randomday.user.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.wolen.randomday.user.model.User;

@Repository
public interface UserDAO {

	// 로그인 아이디를 통한 유저 선택
	
	public User selectUser(
			@Param("loginId") String loginId);
	
	
	// 회원 정보를 통한 회원가입
	
	public int insertUser(
			@Param("loginId") String loginId
			, @Param("password") String password
			, @Param("name") String name
			, @Param("birth") String birth
			, @Param("sex") String sex
			, @Param("email") String email);
	
	
	// 로그인 아이디를 통해 해당 유저가 존재하는지 아닌지 확인
	
	public int selectUserByLoginId(
			@Param("loginId") String loginId);
	
	
	// userId와 password를 통해 회원 접근
	public User selectUserByUserIdAndPass(@Param("userId") int userId, @Param("password") String password);
	
}
