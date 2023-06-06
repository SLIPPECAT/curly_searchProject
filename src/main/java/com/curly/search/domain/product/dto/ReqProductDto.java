package com.curly.search.domain.product.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqProductDto {

	private String product;

	public ReqProductDto() {
	}

	public ReqProductDto(String product) {
		this.product = product;
	}
}
