package org.sopt.domain.product.service;

import lombok.RequiredArgsConstructor;
import org.sopt.domain.product.dto.ProductResponse;
import org.sopt.domain.product.entity.Product;
import org.sopt.domain.product.entity.ProductColor;
import org.sopt.domain.product.entity.ProductImage;
import org.sopt.domain.product.repository.ProductColorRepository;
import org.sopt.domain.product.repository.ProductImageRepository;
import org.sopt.domain.product.repository.ProductRepository;
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

}
