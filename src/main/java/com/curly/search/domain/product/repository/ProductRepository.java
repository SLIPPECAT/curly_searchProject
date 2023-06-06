package com.curly.search.domain.product.repository;

import com.curly.search.domain.product.entity.Product;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query(value = "SELECT * FROM product WHERE MATCH(product_nm) AGAINST(:product)", nativeQuery = true)
	List<Product> findProductByProduct_nm(@Param("product") String product, Pageable pageable);

}
