package com.curly.search.domain.product.controller;

import com.curly.search.domain.product.dto.RespProductDto;
import com.curly.search.domain.product.service.ProductFindService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProductFindController {

	private final ProductFindService findService;

//	@PostMapping("/product/search")
//	@ResponseBody
//	public String searchProduct(ModelMap modelMap, @RequestBody ReqProductDto productDto){
//		log.info(productDto.getProduct());
//
//		List<RespProductDto> result = findService.searchProduct(productDto);
//
//		modelMap.put("result", result);
//
//		return "searchResult";
//
//	}

	@GetMapping("/search")
	public ResponseEntity<List<RespProductDto>> searchProducts(@RequestParam("query") String query,
		@RequestParam(value = "page", defaultValue = "1") int page,
		@RequestParam(value = "size", defaultValue = "5") int size,
		Model model) {
		log.info("[검색어]" + query );

		List<RespProductDto> searchResults = findService.searchProduct(query, page, size);

		model.addAttribute("searchResults", searchResults);
		for (RespProductDto dto : searchResults){
			System.out.println(dto.getProduct());
		}

//		return new Gson().toJson(searchResults);
//		return "home";
		return ResponseEntity.ok(searchResults);

	}

}
