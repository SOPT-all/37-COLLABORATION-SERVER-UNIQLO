package org.sopt.domain.product.controller;

import lombok.RequiredArgsConstructor;
import org.sopt.domain.product.controller.docs.ProductControllerDocs;
import org.sopt.domain.product.dto.ProductInfoResponse;
import org.sopt.domain.product.dto.ProductResponse;
import org.sopt.domain.product.service.ProductService;
import org.sopt.global.api.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController implements ProductControllerDocs {

    private final ProductService productService;

    /**
     * 전체 제품 목록 조회
     */
    @GetMapping
    public ApiResponse<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> products = productService.getAllProducts();
        return ApiResponse.ok(products);
    }

    /**
     * 제품 정보 조회
     */
    @GetMapping("/{productId}")
    public ApiResponse<ProductInfoResponse> getProductById(@PathVariable("productId") Long productId) {
        ProductInfoResponse productInfo = productService.getProductInfo(productId);
        return ApiResponse.ok(productInfo);
    }
}
