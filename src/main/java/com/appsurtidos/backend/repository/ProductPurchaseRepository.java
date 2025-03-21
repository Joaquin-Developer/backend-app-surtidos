package com.appsurtidos.backend.repository;

import com.appsurtidos.backend.models.ProductPurchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPurchaseRepository extends JpaRepository<ProductPurchase, Long> {
}
