package org.sopt.domain.product.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String originPrice;

    private String salePrice;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false)
    private String sizeRange;

    @Enumerated(EnumType.STRING)
    private ProductType productType;

    @Column(nullable = false)
    private float starAverage;

    @Column(nullable = false)
    private int reviewCount;

    // 도메인 로직: 성별과 사이즈 범위 조합 반환
    public String getGenderAndSizeRange() {
        return gender.getDisplayName() + ", " + sizeRange;
    }

    // 도메인 로직: 상품 타입 한글명 반환
    public String getProductTypeDisplayName() {
        return productType != null ? productType.getDisplayName() : null;
    }
}
