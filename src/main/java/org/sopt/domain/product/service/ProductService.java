package org.sopt.domain.product.service;

import org.sopt.domain.product.dto.ProductResponse;

import java.util.List;

public interface ProductService {

    /**
     * 전체 제품 목록 조회
     */
    List<ProductResponse> getAllProducts();
}
