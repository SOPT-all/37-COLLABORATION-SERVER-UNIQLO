package org.sopt.domain.styleHint.repository;

import org.sopt.domain.styleHint.entity.ProductStyleHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductStyleHintRepository extends JpaRepository<ProductStyleHint, Long> {

    @Query("""
        SELECT psh FROM ProductStyleHint psh
        JOIN FETCH psh.styleHint
        WHERE psh.product.id = :productId
    """)
    List<ProductStyleHint> findByProductIdFetchJoin(Long productId);
}
