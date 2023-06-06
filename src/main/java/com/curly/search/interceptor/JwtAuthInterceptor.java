package com.curly.search.interceptor;

import com.curly.search.jwt.JwtUtil;
import com.curly.search.practice.HandlerInterceptor;
import com.curly.search.security.SecurityExceptionDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthInterceptor implements HandlerInterceptor {

	private final JwtUtil jwtUtil;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String token = jwtUtil.resolveToken(request);

		if(token != null) {
			if (!jwtUtil.validateToken(token)){
				jwtExceptionHandler(response, "Token Error", HttpServletResponse.SC_UNAUTHORIZED);
				return false;
			}
			Claims info = jwtUtil.getUserInfoFromToken(token);
			setAuthentication(info.getSubject());
		}
		return true;
	}

	public void setAuthentication(String username) {
		SecurityContext context = SecurityContextHolder.createEmptyContext();
		Authentication authentication = jwtUtil.createAuthentication(username);
		context.setAuthentication(authentication);

		SecurityContextHolder.setContext(context);
	}

	public void jwtExceptionHandler(HttpServletResponse response, String msg, int statusCode) {
		response.setStatus(statusCode);
		response.setContentType("application/json");
		try {
			String json = new ObjectMapper().writeValueAsString(new SecurityExceptionDto(statusCode, msg));
			response.getWriter().write(json);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//		if (request.getRequestURI().equals("/api/post/")) {
//			return true;  // 인터셉트 하지 않음
//		}
//		return preHandle(request, response, handler);
//	}
}
