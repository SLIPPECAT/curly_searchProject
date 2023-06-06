package com.curly.search.config;

import com.curly.search.practice.LoggingInterceptor;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoggingInterceptor())
			.addPathPatterns("/**"); // 모든 요청에 대해 로깅 인터셉트
	}

	@Bean
	public RestTemplate restClient() {

		// 커스텀 클라이언트 설정 (Apache HttpClient 사용)
		HttpClient httpClient = HttpClientBuilder.create()
			.build();

		ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);

		RestTemplate restTemplate = new RestTemplate(requestFactory);

	return restTemplate;


		// 요청/응답 인터셉터 등록
		// restTemplate.getInterceptors().add(new CustomInterceptor());

		// 메시지 컨버터 설정
		// restTemplate.getMessageConverters().add(new CustomMessageConverter());

		// 연결 풀 설정
		// ((HttpComponentsClientHttpRequestFactory) requestFactory).setConnectionRequestTimeout(5000);
		// ((HttpComponentsClientHttpRequestFactory) requestFactory).setMaxTotal(100);
		// ((HttpComponentsClientHttpRequestFactory) requestFactory).setMaxPerRoute(10);

		// 타임아웃 설정
		// restTemplate.setRequestFactory(requestFactory);
		// restTemplate.setReadTimeout(5000);
		// restTemplate.setConnectTimeout(3000);

	}
}





//	@Bean
//	public RestTemplate restTemplate(){
//		return new RestTemplate();
//	}

//	@Bean
//	public ProductCache productCache(){
//		return new ProductCache();
//	}

