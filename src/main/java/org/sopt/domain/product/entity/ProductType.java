package org.sopt.domain.product.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProductType {
    ONLINE("온라인단독"),
    OFFLINE("일부매장제품");

    private final String displayName;
}
