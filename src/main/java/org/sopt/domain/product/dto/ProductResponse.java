package org.sopt.domain.product.dto;

import lombok.Builder;
import org.sopt.domain.product.entity.Product;
import org.sopt.domain.product.entity.ProductColor;
import org.sopt.domain.product.entity.ProductImage;

import java.util.List;
import java.util.stream.Collectors;

@Builder
public record ProductResponse(
        String productImageUrl,
        List<String> colorCode,
        String genderAndSizeRange,
        String name,
        String originPrice,
        String salePrice,
        String productType,
        float starAverage,
        int reviewCount
) {
    public static ProductResponse of(
            Product product,
            List<ProductImage> images,
            List<ProductColor> colors
    ) {
        String mainImageUrl = images.isEmpty() ? null : images.get(0).getProductImageUrl();
        List<String> colorCodes = colors.stream()
                .map(ProductColor::getColorCode)
                .collect(Collectors.toList());

        return ProductResponse.builder()
                .productImageUrl(mainImageUrl)
                .colorCode(colorCodes)
                .genderAndSizeRange(product.getGenderAndSizeRange())
                .name(product.getName())
                .originPrice(product.getOriginPrice())
                .salePrice(product.getSalePrice())
                .productType(product.getProductTypeDisplayName())
                .starAverage(product.getStarAverage())
                .reviewCount(product.getReviewCount())
                .build();
    }
}
