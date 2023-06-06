
package com.curly.search.domain.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

@Getter
@Table(name = "product")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Product {

	@Id
	@Column(name = "id")
	private Long id;

	@Column(name = "product_cd")
	private Integer code;

	@Column(name = "product_nm")
	private String product;

	@Column(name = "product_type_a")
	private String typeA;

	@Column(name = "product_type_b")
	private String typeB;

	@Column(name = "product_type_c")
	private String typeC;

}
