package com.curly.search.practice;

import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
public class LoggingInterceptor implements HandlerInterceptor {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
		String url = request.getRequestURL().toString();
		String method = request.getMethod();
		log.info("Pre-handle: " + method + " " + url);

		return true;
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response,  Object handler,
		@Nullable Exception ex) {

		int status = response.getStatus();
		log.info("afterCompletion: " + status);

	}
}
