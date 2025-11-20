package org.sopt.domain.product.repository;

import org.sopt.domain.product.entity.ProductColor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductColorRepository extends JpaRepository<ProductColor, Long> {

    /**
     * 주어진 Product들에 해당하는 모든 색상 조회
     */
    @Query("SELECT pc FROM ProductColor pc WHERE pc.product.id IN :productIds ORDER BY pc.id ASC")
    List<ProductColor> findAllByProductIds(@Param("productIds") List<Long> productIds);

    /**
     * 특정 Product에 해당하는 모든 색상 조회
     */
    @Query("SELECT pc FROM ProductColor pc WHERE pc.product.id = :productId ORDER BY pc.id ASC")
    List<ProductColor> findAllByProductId(@Param("productId") Long productId);

    List<ProductColor> findByProductId(Long productId);
}
