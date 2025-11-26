package org.sopt.domain.product.dto;

import org.sopt.domain.review.entity.Review;

import java.time.format.DateTimeFormatter;
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

        private static final DateTimeFormatter DATE_FORMATTER =
                DateTimeFormatter.ofPattern("yyyy-MM-dd");

        public static ReviewSummary from(Review review) {

            String height = (review.getHeight() == null || review.getHeight().isBlank())
                    ? "선택하지않음"
                    : review.getHeight();

            String gender = (review.getGender() == null)
                    ? "선택하지않음"
                    : review.getGender().getDisplayNameKr();

            // yyyy-MM-dd 형태로 변환
            String createdAt = review.getCreatedAt()
                    .toLocalDate()
                    .format(DATE_FORMATTER);

            return new ReviewSummary(
                    review.getTitle(),
                    review.getContent(),
                    review.getStar(),
                    createdAt,
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
