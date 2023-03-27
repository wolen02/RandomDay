package com.wolen.randomday.common;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class PermissionInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(
				HttpServletRequest request
				, HttpServletResponse response
				, Object handler) throws IOException {
		
		HttpSession session = request.getSession();
		
		// null 을 줄수도 있기 때문에 null을 포함 가능한 Integer 사용
		Integer userId = (Integer)session.getAttribute("userId");
		
		String uri = request.getRequestURI();
		
		if(userId != null) {
			// 로그인이 되어있는 경우
			
			if(uri.startsWith("/randomday/signin/view") || uri.startsWith("/randomday/signup/view")) {
				
				// 시작페이지로 이동
				response.sendRedirect("/randomday/start/view");
				return false;
				}
			
			}else {
				// 로그인이 되어 있지 않은 경우
				if(uri.startsWith("/randomday/user/") || uri.startsWith("/randomday/domestic") || uri.startsWith("/randomday/foreign/")) {
					// 로그인 페이지 이동
					response.sendRedirect("/randomday/signin/view");
					return false;
					}
				}
		
		return true;
	
	}
	
	@Override
	public void postHandle(
			HttpServletRequest request
			, HttpServletResponse response
			, Object handler
			, ModelAndView modelAndView) {
		
	}
	
	@Override
	public void afterCompletion(
			HttpServletRequest request
			, HttpServletResponse response
			, Object handler
			, Exception ex) {
		
	}
	
	
}
