package org.sopt.domain.product.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.sopt.domain.product.dto.ProductInfoResponse;
import org.sopt.domain.product.dto.ProductResponse;
import org.sopt.domain.product.entity.*;
import org.sopt.domain.product.repository.ProductColorRepository;
import org.sopt.domain.product.repository.ProductImageRepository;
import org.sopt.domain.product.repository.ProductRepository;
import org.sopt.global.api.ErrorCode;
import org.sopt.global.api.GeneralException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductImageRepository productImageRepository;

    @Mock
    private ProductColorRepository productColorRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    @DisplayName("전체 제품 목록 조회 - 성공")
    void getAllProducts_Success() {
        // given
        Product product1 = createProduct(1L, "제품1", "10,000", null);
        Product product2 = createProduct(2L, "제품2", "20,000", "15,000");

        List<Product> products = Arrays.asList(product1, product2);

        ProductImage image1 = createProductImage(1L, product1, "image1.jpg");
        ProductImage image2 = createProductImage(2L, product2, "image2.jpg");

        ProductColor color1 = createProductColor(1L, product1, "#000000", "BLACK");
        ProductColor color2 = createProductColor(2L, product2, "#FFFFFF", "WHITE");

        when(productRepository.findAllProducts()).thenReturn(products);
        when(productImageRepository.findAllByProductIds(anyList()))
                .thenReturn(Arrays.asList(image1, image2));
        when(productColorRepository.findAllByProductIds(anyList()))
                .thenReturn(Arrays.asList(color1, color2));

        // when
        List<ProductResponse> result = productService.getAllProducts();

        // then
        assertThat(result).hasSize(2);
        assertThat(result.get(0).name()).isEqualTo("제품1");
        assertThat(result.get(0).productImageUrl()).isEqualTo("image1.jpg");
        assertThat(result.get(0).colorCode()).contains("#000000");
        assertThat(result.get(1).name()).isEqualTo("제품2");
    }

    @Test
    @DisplayName("전체 제품 목록 조회 - 제품이 없는 경우")
    void getAllProducts_EmptyList() {
        // given
        when(productRepository.findAllProducts()).thenReturn(Collections.emptyList());

        // when
        List<ProductResponse> result = productService.getAllProducts();

        // then
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("제품 상세 정보 조회 - 성공")
    void getProductInfo_Success() {
        // given
        Long productId = 1L;
        Product product = createProduct(productId, "밀라노립니트재킷", "49,900", null);

        ProductImage image = createProductImage(1L, product, "example.com");
        ProductColor color = createProductColor(1L, product, "#000000", "09 BLACK");

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(productImageRepository.findAllByProductId(productId))
                .thenReturn(Collections.singletonList(image));
        when(productColorRepository.findAllByProductId(productId))
                .thenReturn(Collections.singletonList(color));

        // when
        ProductInfoResponse result = productService.getProductInfo(productId);

        // then
        assertThat(result).isNotNull();
        assertThat(result.productId()).isEqualTo("1");
        assertThat(result.name()).isEqualTo("밀라노립니트재킷");
        assertThat(result.originPrice()).isEqualTo("49,900");
        assertThat(result.salePrice()).isNull();
        assertThat(result.productImageUrl()).isEqualTo("example.com");
        assertThat(result.colorCode()).contains("#000000");
        assertThat(result.color()).containsEntry("#000000", "09 BLACK");
    }

    @Test
    @DisplayName("제품 상세 정보 조회 - 존재하지 않는 제품")
    void getProductInfo_ProductNotFound() {
        // given
        Long productId = 999L;
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        // when & then
        assertThatThrownBy(() -> productService.getProductInfo(productId))
                .isInstanceOf(GeneralException.class)
                .hasFieldOrPropertyWithValue("errorCode", ErrorCode.PRODUCT_NOT_FOUND);
    }

    // Helper
    private Product createProduct(Long id, String name, String originPrice, String salePrice) {
        return Product.builder()
                .id(id)
                .name(name)
                .originPrice(originPrice)
                .salePrice(salePrice)
                .gender(Gender.FEMALE)
                .sizeRange("XS~3XL")
                .productType(ProductType.ONLINE)
                .starAverage(4.8f)
                .reviewCount(10)
                .build();
    }

    private ProductImage createProductImage(Long id, Product product, String url) {
        return ProductImage.builder()
                .id(id)
                .product(product)
                .productImageUrl(url)
                .build();
    }

    private ProductColor createProductColor(Long id, Product product, String code, String name) {
        return ProductColor.builder()
                .id(id)
                .product(product)
                .colorCode(code)
                .colorName(name)
                .build();
    }
}