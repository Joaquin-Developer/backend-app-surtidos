package com.appsurtidos.backend.repository;

import com.appsurtidos.backend.models.AllProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;

@Repository
public interface AllProductRepository extends JpaRepository<AllProducts, LocalDateTime> {
}
