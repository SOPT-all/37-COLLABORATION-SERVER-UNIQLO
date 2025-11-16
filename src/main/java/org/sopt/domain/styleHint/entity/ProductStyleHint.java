package org.sopt.domain.styleHint.entity;

import jakarta.persistence.*;
import lombok.*;

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
}
