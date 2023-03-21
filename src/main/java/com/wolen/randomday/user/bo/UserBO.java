package com.wolen.randomday.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wolen.randomday.common.EncryptUtils;
import com.wolen.randomday.common.FileManagerService;
import com.wolen.randomday.user.dao.UserDAO;
import com.wolen.randomday.user.model.User;

@Service
public class UserBO {
	
	@Autowired
	private UserDAO userDAO;

	// 아이디와 비밀번호를 통해 로그인
	
	public User getUser(
			String loginId) {
			
		
		User user = userDAO.selectUser(loginId);
		
		if(user == null) {
			return null;
		}else {
			
			return user;
			
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
		
		String encryptPass = EncryptUtils.md5(password);
		
		return userDAO.insertUser(loginId, encryptPass, name, birth, sex, email);
		
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
	
	
	// 비밀번호 변경
	
	public int modifyPass(int userId, String confirmPassword, String password) {

		// 기존비밀번호
		String encryptConfirmPass = EncryptUtils.md5(confirmPassword);
		
		// 새로운 비밀번호
		String encryptPass = EncryptUtils.md5(password);
		
		User user = userDAO.selectUserByUserIdAndPass(userId, encryptConfirmPass);
		
		
		if(user != null) {
			return userDAO.updateNewPassById(userId, encryptPass);
		}else {
			return 0;
		}
				
	}
	
	// 기존 프로필 제거 후 새로 등록
	
	public int modifyProfile(int userId, String imagePath) {
		

		
		User user = userDAO.selectUserByuserId(userId);
		
		// 유저를 통해 이미지 있는 지 확인
		
		String image = user.getImagePath();
		
		if(image != null) {
			
			// 기존 이미지 삭제
			int count = userDAO.deleteImage(userId);
			
			// 이미지 등록
			if(count == 1) {
				return userDAO.updateImage(userId, imagePath);
			}else {
				return 0;
			}
			
		}else {
			return userDAO.updateImage(userId, imagePath);
		}
		

		
	}
	
}
