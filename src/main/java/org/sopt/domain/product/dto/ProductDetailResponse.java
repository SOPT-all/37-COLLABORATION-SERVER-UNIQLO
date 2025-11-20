package org.sopt.domain.product.dto;

import org.sopt.domain.product.entity.ProductDetail;

import java.util.Arrays;
import java.util.List;

public record ProductDetailResponse(
        List<String> detailImageUrlList,
        String detailText,
        List<String> descriptionTextList,
        List<String> featureDetailTextList,
        List<String> sizeDetailTextList
) {

    public static ProductDetailResponse from(ProductDetail detail) {

        List<String> imageList = Arrays.stream(detail.getDetailImageUrl().split(","))
                .map(String::trim)
                .toList();

        List<String> descriptionList = Arrays.stream(detail.getDescriptionText().split("&"))
                .map(String::trim)
                .toList();

        List<String> featureList = Arrays.stream(detail.getFeatureDetailText().split("&"))
                .map(String::trim)
                .toList();

        List<String> sizeList = Arrays.stream(detail.getSizeDetailText().split("&"))
                .map(String::trim)
                .toList();

        return new ProductDetailResponse(
                imageList,
                detail.getDetailText(),
                descriptionList,
                featureList,
                sizeList
        );
    }
}
