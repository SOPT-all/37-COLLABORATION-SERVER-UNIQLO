package org.sopt.domain.product.repository;

import org.sopt.domain.product.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

    /**
     * Product들에 해당하는 모든 이미지 조회
     */
    @Query("SELECT pi FROM ProductImage pi WHERE pi.product.id IN :productIds ORDER BY pi.id ASC")
    List<ProductImage> findAllByProductIds(@Param("productIds") List<Long> productIds);
}