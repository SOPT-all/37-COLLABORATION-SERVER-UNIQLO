package org.sopt.domain.review.controller;

import lombok.RequiredArgsConstructor;
import org.sopt.domain.review.service.ReviewService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
}
