//package com.curly.search;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import javax.annotation.PostConstruct;
//import java.util.List;
//import org.springframework.stereotype.Component;
//
//@RequiredArgsConstructor
//@Component
//public class ProductCache {
//
//	private ProductFindService productService;
//
//	private List<Product> cachedData;
//
//	@PostConstruct
//	public void initializeCache() {
//		cachedData = productService.getAllProducts();
//	}
//
//	public List<Product> getCachedData() {
//		return cachedData;
//	}
//}
//
