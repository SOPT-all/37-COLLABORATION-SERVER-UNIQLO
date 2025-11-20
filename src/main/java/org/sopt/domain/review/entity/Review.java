package org.sopt.domain.review.entity;

import jakarta.persistence.*;
import lombok.*;
import org.sopt.domain.product.entity.Gender;
import org.sopt.domain.product.entity.Product;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Float star;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = true)
    private String height;

    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false)
    private Integer recommend;

    @Column(nullable = false)
    private String size;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private String fit;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
}
