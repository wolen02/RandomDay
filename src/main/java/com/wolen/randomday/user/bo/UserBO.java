package com.wolen.randomday.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wolen.randomday.user.dao.UserDAO;
import com.wolen.randomday.user.model.User;

@Service
public class UserBO {
	
	@Autowired
	private UserDAO userDAO;

	// 아이디와 비밀번호를 통해 로그인
	
	public String getUser(
			String loginId
			, String password) {
		
		User user = userDAO.selectUser(loginId);
		
		if(user == null) {
			return null;
		}else {
			String userPassword = user.getPassword();
			
			if(password.equals(userPassword)) {
				return "success";
			}else {
				return "failPass";
			}
		}

	}
	
	
	// 회원정보를 통한 사용자 회원가입(BO에서 문자열을 int 로 바꿔야함)
	
	public int addUser(
			String loginId
			, String password
			, String name
			, String birth
			, String sex
			, String email) {
		
		return userDAO.insertUser(loginId, password, name, birth, sex, email);
		
	}
	
	// 아이디를 통해 해당 아이디가 중복되는 지 확인
	
	public boolean isduplicatedId(String loginId) {
		
		int count = userDAO.selectUserByLoginId(loginId);
		
		if(count == 1) {
			return true;
		}else {
			return false;
		}
		
	}
	
	
	
}
