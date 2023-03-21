package com.wolen.randomday.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtils {

	// md5를 통한 암호화 기능(static은 객체 생성 없이사용가능)
	public static String md5(String message) {
		
		String resultData = "";
		
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("md5");
			
			byte[] bytes = message.getBytes();
			md.update(bytes);
			
			byte[] digest = md.digest();
			
			for(int i = 0; i < digest.length; i++) {
				resultData += Integer.toHexString(digest[i] & 0xff);
			}
			
			
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
		}
		
		return resultData;
		
		
		
	}
	
	
}
