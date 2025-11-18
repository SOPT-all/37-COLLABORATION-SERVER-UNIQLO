package org.sopt.domain.product.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import org.sopt.domain.product.entity.Product;
import org.sopt.domain.product.entity.ProductColor;
import org.sopt.domain.product.entity.ProductImage;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ProductInfoResponse(
        String productId,
        String productImageUrl,
        List<String> colorCode,
        Map<String, String> color,
        String name,
        String originPrice,
        String salePrice,
        float starAverage
) {
    public static ProductInfoResponse of(
            Product product,
            List<ProductImage> images,
            List<ProductColor> colors
    ) {
        String mainImageUrl = images.isEmpty() ? null : images.get(0).getProductImageUrl();

        List<String> colorCodes = colors.stream()
                .map(ProductColor::getColorCode)
                .collect(Collectors.toList());

        Map<String, String> colorMap = colors.stream()
                .collect(Collectors.toMap(
                        ProductColor::getColorCode,
                        ProductColor::getColorName,
                        (existing, replacement) -> existing
                ));

        return ProductInfoResponse.builder()
                .productId(String.valueOf(product.getId()))
                .productImageUrl(mainImageUrl)
                .colorCode(colorCodes)
                .color(colorMap)
                .name(product.getName())
                .originPrice(product.getOriginPrice())
                .salePrice(product.getSalePrice())
                .starAverage(product.getStarAverage())
                .build();
    }
}