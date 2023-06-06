package com.curly.search.config;//package com.example.jwt_prac.config;

import com.curly.search.filter.JwtAuthFilter;
import com.curly.search.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
@RequiredArgsConstructor
@EnableWebSecurity // 스프링 Security 지원을 가능하게 함
@EnableGlobalMethodSecurity(securedEnabled = true) // @Secured 어노테이션 활성화
public class WebSecurityConfig {

    private final JwtUtil jwtUtil;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        // h2-console 사용 및 resources 접근 허용 설정
        return (web) -> web.ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
// 기본 설정인 Session 방식은 사용하지 않고 JWT 방식을 사용하기 위한 설정
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests()
                .antMatchers("/**").permitAll()
                .antMatchers("/api/**").permitAll()
                .antMatchers(HttpMethod.GET,"/api/posts/**").permitAll()
                .antMatchers("/api/comments/**").permitAll()
                .anyRequest().authenticated()
                .and()
                // JWT 인증/인가를 사용하기 위한 설정
                .addFilterBefore(new JwtAuthFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);
//                .addFilterBefore(JwtExceptionHandlerFilter, JwtAuthFilter.class);
                // JWT 인증 전 인증 인가 예외 처리
//                .addFilterBefore(new)
        http.cors();

        http.exceptionHandling().accessDeniedPage("/api/user/forbidden");

        return http.build();
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource(){

        CorsConfiguration config = new CorsConfiguration();
// 사전에 약속된 출처를 명시
        config.addAllowedOrigin("http://localhost:3000");
//        config.addAllowedOrigin("http://hanghae99-istagrem.s3-website.ap-northeast-2.amazonaws.com");
//        config.addAllowedOrigin("http://i-stagrem.s3-website.ap-northeast-2.amazonaws.com");
        config.addExposedHeader(JwtUtil.AUTHORIZATION_HEADER);
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        config.setAllowCredentials(true);
        config.validateAllowCredentials();

// 어떤 경로에 이 설정을 적용할 지 명시합니다. (여기서는 전체 경로)
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
