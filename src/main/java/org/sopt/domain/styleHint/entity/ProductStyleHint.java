package org.sopt.domain.styleHint.entity;

import jakarta.persistence.*;
import lombok.*;
import org.sopt.domain.product.entity.Product;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
@Table(name = "product_style_hint")
public class ProductStyleHint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    private StyleHint styleHint;
}
