package org.sopt.domain.product.repository;

import org.sopt.domain.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * 전체 제품 목록 조회
     */
    @Query("SELECT p FROM Product p ORDER BY p.id")
    List<Product> findAllProducts();
}
