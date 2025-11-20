package org.sopt.domain.product.dto;

import org.sopt.domain.product.entity.Product;
import org.sopt.domain.product.entity.ProductColor;
import org.sopt.domain.product.entity.ProductImage;

import java.util.List;

public record ProductSummaryResponse(
        Long id,
        String name,
        String originPrice,
        String salePrice,
        float starAverage,
        int reviewCount,
        List<String> productImageUrlList,
        List<ColorDto> colorList
) {

    public static ProductSummaryResponse from(
            Product product,
            List<ProductImage> images,
            List<ProductColor> colors
    ) {
        List<String> imageUrls = images.stream()
                .map(ProductImage::getProductImageUrl)
                .toList();

        List<ColorDto> colorDtos = colors.stream()
                .map(c -> new ColorDto(c.getColorName(), c.getColorCode()))
                .toList();

        return new ProductSummaryResponse(
                product.getId(),
                product.getName(),
                product.getOriginPrice(),
                product.getSalePrice(),
                product.getStarAverage(),
                product.getReviewCount(),
                imageUrls,
                colorDtos
        );
    }

    public static record ColorDto(
            String colorName,
            String colorCode
    ) {}
}
