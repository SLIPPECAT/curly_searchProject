//
//package com.curly.search.controller;
//
//import static org.mockito.Mockito.*;
//
//import com.curly.search.dto.RespProductDto;
//import com.curly.search.entity.Product;
//import com.curly.search.service.ProductFindService;
//import java.util.Arrays;
//import java.util.List;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//@ExtendWith(SpringExtension.class)
//@EnableJpaRepositories
//@WebMvcTest(ProductViewController.class)
//public class ProductViewControllerTest {
//
//	@Autowired
//	private MockMvc mockMvc;
//
//	@MockBean
//	private ProductFindService findService;
//
//	@Test
//	void testProductViewController() throws Exception {
//		// Mock 객체 생성
//		ProductFindService mockFindService = mock(ProductFindService.class);
//
//		// Mock 객체를 ProductViewController에 주입
//		ProductViewController productViewController = new ProductViewController(mockFindService);
//
//		// mockMvc를 사용하여 테스트 진행
//		mockMvc = MockMvcBuilders.standaloneSetup(productViewController).build();
//
//		Product product1 = new Product(1L, 100, "아메리카노", "타입1","타입2", "타입3");
//		Product product2 = new Product(2L, 200, "스타벅스 아메리카노", "타입1","타입2", "타입3");
//
//		List<RespProductDto> mockResult = Arrays.asList(
//			new RespProductDto(product1), new RespProductDto(product2)
//		);
//
//		// Mock 객체에 동작 설정
//		when(findService.searchProduct("아메리카노", 1, 10)).thenReturn(mockResult);
//
//		// GET 요청 전송 및 응답 검증
//		mockMvc.perform(MockMvcRequestBuilders.get("/product/search")
//				.param("query", "아메리카노")
//				.param("page", "1")
//				.param("size", "10"))
//				.andExpect(MockMvcResultMatchers.status().isOk())
//				.andExpect(MockMvcResultMatchers.view().name("searchResult"))
//				.andExpect(MockMvcResultMatchers.model().attributeExists("result"))
//				.andExpect(MockMvcResultMatchers.model().attribute("result", mockResult));
//
//		// Mock 객체의 동작이 호출되었는지 검증
//		/*verify(findService, times(1)).searchProduct("아메리카노", 1, 10);*/
//	}
//
//}
