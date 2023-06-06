package com.curly.search.domain.product.service;

import com.curly.search.aop.Timer;
import com.curly.search.domain.product.repository.ProductRepository;
import com.curly.search.domain.product.dto.RespProductDto;
import com.curly.search.domain.product.entity.Product;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductFindService {

	private final ProductRepository repository;

//	@Cacheable("products")
	@Timer
	public List<RespProductDto> searchProduct(String query, int page, int size){

		Pageable pageable = PageRequest.of(page -1, size);

		List<Product> products = repository.findProductByProduct_nm(query, pageable);

		List<RespProductDto> result = new ArrayList<>();

		for (Product product : products){
			result.add(new RespProductDto(product));
//			log.info(product.getProduct());
		}
		return result;

	}

	public List<RespProductDto> getRelatedProducts(String query, int page, int size) {

		Pageable pageable = PageRequest.of(page -1, size);

		List<Product> products = repository.findProductByProduct_nm(query, pageable);

		List<RespProductDto> result = new ArrayList<>();

		for (Product product : products){
			result.add(new RespProductDto(product));
		}
		return result;
	}

	public List<Product> getAllProducts() {

		List<Product> products = repository.findAll();

		return products;

	}
}
