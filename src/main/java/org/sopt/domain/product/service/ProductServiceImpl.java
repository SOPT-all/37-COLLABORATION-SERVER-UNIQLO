package org.sopt.domain.product.service;

import lombok.RequiredArgsConstructor;
import org.sopt.domain.product.dto.*;
import org.sopt.domain.product.entity.Product;
import org.sopt.domain.product.entity.ProductColor;
import org.sopt.domain.product.entity.ProductImage;
import org.sopt.domain.product.repository.ProductColorRepository;
import org.sopt.domain.product.repository.ProductImageRepository;
import org.sopt.domain.product.repository.ProductRepository;
import org.sopt.domain.review.entity.Review;
import org.sopt.domain.review.repository.ReviewRepository;
import org.sopt.domain.styleHint.entity.ProductStyleHint;
import org.sopt.domain.styleHint.repository.ProductStyleHintRepository;
import org.sopt.global.api.ErrorCode;
import org.sopt.global.api.GeneralException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final ProductColorRepository productColorRepository;
    private final ProductStyleHintRepository productStyleHintRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAllProducts();
        if (products.isEmpty()) {
            return Collections.emptyList();
        }

        List<Long> productIds = products.stream()
                .map(Product::getId)
                .collect(Collectors.toList());

        // 이미지, 색상 조회
        List<ProductImage> allImages = productImageRepository.findAllByProductIds(productIds);
        List<ProductColor> allColors = productColorRepository.findAllByProductIds(productIds);

        // 이미지, 색상 그룹핑
        Map<Long, List<ProductImage>> imagesByProductId = allImages.stream()
                .collect(Collectors.groupingBy(img -> img.getProduct().getId()));
        Map<Long, List<ProductColor>> colorsByProductId = allColors.stream()
                .collect(Collectors.groupingBy(color -> color.getProduct().getId()));

        return products.stream()
                .map(product -> ProductResponse.of(
                        product,
                        imagesByProductId.getOrDefault(product.getId(), Collections.emptyList()),
                        colorsByProductId.getOrDefault(product.getId(), Collections.emptyList())
                ))
                .collect(Collectors.toList());
    }

    @Override
    public ProductInfoResponse getProductInfo(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new GeneralException(ErrorCode.PRODUCT_NOT_FOUND));

        List<ProductImage> images = productImageRepository.findAllByProductId(productId);
        List<ProductColor> colors = productColorRepository.findAllByProductId(productId);

        return ProductInfoResponse.of(product, images, colors);
    }

    @Override
    public ProductSummaryResponse getProductDetail(Long productId) {

        // 1. 상품 조회 (존재하지 않으면 예외 발생)
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new GeneralException(ErrorCode.PRODUCT_NOT_FOUND));

        // 2. 해당 상품 이미지 조회
        List<ProductImage> images = productImageRepository.findByProductId(productId);

        // 3. 해당 상품 색상 조회
        List<ProductColor> colors = productColorRepository.findByProductId(productId);

        // 4. DTO로 변환 후 반환
        return ProductSummaryResponse.from(product, images, colors);
    }

    @Override
    public StyleHintImageResponse getProductHintImage(Long productId) {
        // 페치조인으로 N+1 제거
        List<ProductStyleHint> hints =
                productStyleHintRepository.findByProductIdFetchJoin(productId);

        return StyleHintImageResponse.from(hints);
    }

    @Override
    public ReviewListResponse getProductReviews(Long productId) {

        productRepository.findById(productId)
                .orElseThrow(() -> new GeneralException(ErrorCode.PRODUCT_NOT_FOUND));

        List<Review> reviews = reviewRepository.findByProductId(productId);

        return ReviewListResponse.from(reviews);
    }
}
