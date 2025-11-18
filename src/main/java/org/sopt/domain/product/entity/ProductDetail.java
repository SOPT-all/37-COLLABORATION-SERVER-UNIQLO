package org.sopt.domain.product.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
@Table(name = "product_detail")
public class ProductDetail {

    @Id
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    private Product product;

    @Column(nullable = false)
    private String detailImageUrl;

    @Column(nullable = false)
    private String detailText;

    @Column(nullable = false)
    private String descriptionText;

    @Column(nullable = false)
    private String featureDetailText;

    @Column(nullable = false)
    private String sizeDetailText;
}
