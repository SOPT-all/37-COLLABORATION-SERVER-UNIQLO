package org.sopt.domain.product.dto;

import org.sopt.domain.review.entity.Review;

import java.util.List;

public record ReviewListResponse(
        List<ReviewSummary> reviews
) {

    public static ReviewListResponse from(List<Review> reviewList) {
        List<ReviewSummary> mapped = reviewList.stream()
                .map(ReviewSummary::from)
                .toList();

        return new ReviewListResponse(mapped);
    }

    public static record ReviewSummary(
            String title,
            String content,
            Float star,
            String createdAt,
            String height,
            String gender,
            Integer recommend,
            String size,
            String color,
            String fit
    ) {

        public static ReviewSummary from(Review review) {

            String height = (review.getHeight() == null || review.getHeight().isBlank())
                    ? "선택하지않음"
                    : review.getHeight();

            String gender = (review.getGender() == null)
                    ? "선택하지않음"
                    : review.getGender().getDisplayNameKr();  // ★ 변경 포인트

            return new ReviewSummary(
                    review.getTitle(),
                    review.getContent(),
                    review.getStar(),
                    review.getCreatedAt().toString(),
                    height,
                    gender,
                    review.getRecommend(),
                    review.getSize(),
                    review.getColor(),
                    review.getFit()
            );
        }
    }
}
