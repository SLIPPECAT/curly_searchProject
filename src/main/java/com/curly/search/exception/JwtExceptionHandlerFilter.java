package com.curly.search.exception;//package project.instatgram.exception;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.JwtException;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Map;
//
//@Slf4j
//@RequiredArgsConstructor
//public class JwtExceptionHandlerFilter extends OncePerRequestFilter {
//
//    private final ObjectMapper om;
//    public static final String DEFAULT_TOKEN_ERROR_MESSAGE = "토큰 값이 잘못되었습니다. 다시 로그인 해주세요.";
//    public static final String TOKEN_EXPIRES_MESSAGE = "로그인이 만료되었습니다. 다시 로그인 해주세요.";
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//
//        try {
//            filterChain.doFilter(request, response);
//        } catch (JwtException e) {
//
//            String errorMsg = DEFAULT_TOKEN_ERROR_MESSAGE;
//
//            if (e.getClass().equals(ExpiredJwtException.class)) {
//                log.error("토큰 만료 : {}", e.getMessage());
//                errorMsg = TOKEN_EXPIRES_MESSAGE;
//            } else {
//                log.error("토큰 오류 : {}", e.getMessage());
//            }
//
//            Map<String, String> errorRes = Map.of("message", errorMsg);
//
//            String responseJson = om.writeValueAsString(errorRes);
//
//            response.setCharacterEncoding("UTF-8");
//            response.setStatus(HttpStatus.UNAUTHORIZED.value());
//            response.getWriter().println(responseJson);
//        }
//    }
//}