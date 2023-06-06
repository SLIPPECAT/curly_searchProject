package com.curly.search.service;

import com.curly.search.domain.product.service.ProductFindService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductFindServiceTest2 {

	@Autowired
	ProductFindService productFindService;

	@Test
	@DisplayName("상품 검색 결과 5개 반환")
	void searchProduct(){

		String query = "아메리카노";
		int page = 1;
		int size = 5;

		var result = productFindService.searchProduct(query, page, size);

		Assertions.assertEquals(size, result.size());

	}

}
