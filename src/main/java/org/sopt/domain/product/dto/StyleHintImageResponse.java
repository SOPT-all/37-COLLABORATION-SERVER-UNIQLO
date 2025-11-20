package org.sopt.domain.product.dto;

import org.sopt.domain.styleHint.entity.ProductStyleHint;

import java.util.List;

public record StyleHintImageResponse(
        List<String> styleHintImageUrlList
) {

    public static StyleHintImageResponse from(List<ProductStyleHint> productStyleHints) {

        List<String> imageUrls = productStyleHints.stream()
                .map(ph -> ph.getStyleHint().getStyleHintImageUrl())
                .toList();

        return new StyleHintImageResponse(imageUrls);
    }
}
