package org.sopt.domain.product.service;

import org.sopt.domain.product.dto.ProductInfoResponse;
import org.sopt.domain.product.dto.ProductResponse;
import org.sopt.domain.product.dto.ProductSummaryResponse;

import java.util.List;

public interface ProductService {

    /**
     * 전체 제품 목록 조회
     */
    List<ProductResponse> getAllProducts();

    /**
     * 제품 상세 정보 조회
     */
    ProductInfoResponse getProductInfo(Long productId);

    ProductSummaryResponse getProductDetail(Long productId);
}
