package com.ecommerce.infrastructure.adapters.out.jpa.repository;

import com.ecommerce.infrastructure.adapters.out.jpa.entity.Price;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceRepository extends CrudRepository<Price, Integer> {

    @Query("""
            SELECT p
            FROM Price p
            JOIN p.brand b
            WHERE b.id = :brandId AND p.productId = :productId AND (:appDate BETWEEN p.startDate AND p.endDate)
            ORDER BY p.priority ASC
            """)
    List<Price> findByBrandProductAndDate(Integer brandId, Integer productId, LocalDateTime appDate);

}
