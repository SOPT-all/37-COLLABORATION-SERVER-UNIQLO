package org.sopt.domain.styleHint.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
@Table(name = "style_hint")
public class StyleHint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
