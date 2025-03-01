package com.appsurtidos.backend.repository;

import com.appsurtidos.backend.models.AllProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AllProductRepository extends JpaRepository<AllProducts, LocalDateTime> {
    @Query(value = """
        WITH products AS (
            SELECT
                p.scraping_date,
                s.name AS supermarket,
                p.ean,
                p.name,
                CONCAT(p.currency, ' ', p.price) AS price,
                ROW_NUMBER() OVER (PARTITION BY p.name ORDER BY p.scraping_date DESC) AS rn
            FROM all_products p
            JOIN supermarket_chains s ON p.supermarket_chain_id = s.id
            WHERE p.ean = -1 and s.id = :supermarketChainId
        )
        SELECT scraping_date, supermarket, ean, name, price
        FROM products
        WHERE rn = 1;
    """, nativeQuery = true)
    List<Object[]> findLatestProductsBySupermarket(@Param("supermarketChainId") Long supermarketChainId);
}
