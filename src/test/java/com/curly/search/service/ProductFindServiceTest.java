package com.curly.search.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.curly.search.domain.product.dto.RespProductDto;
import com.curly.search.domain.product.entity.Product;
import com.curly.search.domain.product.repository.ProductRepository;
import com.curly.search.domain.product.service.ProductFindService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;


//@SpringBootTest
public class ProductFindServiceTest {

	// ProductFindService의 이느턴스를 생성
//	@InjectMocks
	@Mock
	ProductFindService service;

	@Mock
	ProductRepository repository;

	@BeforeEach
	public void setup(){
		MockitoAnnotations.initMocks(this);
		service = new ProductFindService(repository);
	}

	@Test
	@DisplayName("상품 검색 결과 5개 반환")
	public void searchProduct(){
		// Mock 데이터 생성
		List<Product> mockProducts = new ArrayList<>();
		mockProducts.add(new Product(1L, 100, "아메리카노", "타입1","타입2", "타입3"));
		mockProducts.add(new Product(2L, 200, "카페라떼", "타입1","타입2", "타입3"));

//		 Mock 객체에 동작 설정
		when(repository.findProductByProduct_nm("query", PageRequest.of(0, 10))).thenReturn(mockProducts);

		List<RespProductDto> result = service.searchProduct("query", 1, 10);

		// 결과 검증
		assertEquals(2, result.size());




		assertEquals("아메리카노", result.get(0).getProduct());
		assertEquals("카페라떼", result.get(1).getProduct());
//
		// Mock 객체의 동작이 호출되었는지 검증
		verify(repository, times(1)).findProductByProduct_nm("query", PageRequest.of(0, 10));


	}

/*	@Autowired
	ProductFindService productFindService;

	@Test
	@DisplayName("상품 검색 결과 5개 반환")
	void searchProduct(){

		String query = "아메리카노";
		int page = 1;
		int size = 5;

		var result = productFindService.searchProduct(query, page, size);

		Assertions.assertEquals(size, result.size());

	}*/
}
