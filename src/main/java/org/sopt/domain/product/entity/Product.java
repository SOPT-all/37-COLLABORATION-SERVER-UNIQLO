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
    private Gender gender;

    @Column(nullable = false)
    private String sizeRange;

    private type productType;

    @Column(nullable = false)
    private float starAverage;

    @Column(nullable = false)
    private int reviewCount;

}
