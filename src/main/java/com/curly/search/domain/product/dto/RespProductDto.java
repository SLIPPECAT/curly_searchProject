package com.curly.search.domain.product.dto;


import com.curly.search.domain.product.entity.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RespProductDto {

	private String product;
	private String typeA;
	private String typeB;
	private String typeC;
	private Long code;

	public RespProductDto(Product product) {
		this.product = product.getProduct();
		this.typeA = product.getTypeA();
		this.typeB = product.getTypeB();
		this.typeC = product.getTypeC();
		this.code = Long.valueOf(product.getCode());
	}
}
