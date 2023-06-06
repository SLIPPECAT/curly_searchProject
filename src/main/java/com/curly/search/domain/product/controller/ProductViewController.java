package com.curly.search.domain.product.controller;

import com.curly.search.domain.product.dto.RespProductDto;
import com.curly.search.domain.product.service.ProductFindService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ProductViewController {

	private final ProductFindService findService;

	@GetMapping("/")
	public String home(){
		return "home";
	}

	@GetMapping("/product/search")
	public String searchProduct(ModelMap modelMap, @RequestParam(value = "query") String query,
		@RequestParam(value = "page", defaultValue = "1") int page,
		@RequestParam(value = "size", defaultValue = "10") int size){
//		log.info("[검색어]" + query );

		List<RespProductDto> result = findService.searchProduct(query, page, size);

		modelMap.put("result", result);

		return "searchResult";

	}

//	@GetMapping("/product/related")
//	@ResponseBody
//	public List<RespProductDto> getRelatedProducts(@RequestParam("query") String query,
//		@RequestParam(value = "page", defaultValue = "1") int page,
//		@RequestParam(value = "size", defaultValue = "5") int size) {
//		return findService.getRelatedProducts(query, page, size);
//	}

	//한글 영어 번역 동의

}
