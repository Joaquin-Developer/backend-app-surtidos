package com.appsurtidos.backend.repository;

import com.appsurtidos.backend.models.ProductPurchase;
import com.appsurtidos.backend.models.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductPurchaseRepository extends JpaRepository<ProductPurchase, Long> {
    List<ProductPurchase> findByPurchase(Purchase purchase);
}
