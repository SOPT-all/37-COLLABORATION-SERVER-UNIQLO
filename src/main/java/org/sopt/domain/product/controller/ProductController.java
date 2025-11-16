package org.sopt.domain.product.controller;

import lombok.RequiredArgsConstructor;
import org.sopt.domain.product.service.ProductService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
}
