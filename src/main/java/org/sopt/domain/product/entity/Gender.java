package org.sopt.domain.product.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Gender {
    MALE("MEN", "남성"),
    FEMALE("WOMEN", "여성");

    private final String displayNameEng;
    private final String displayNameKr;
}
