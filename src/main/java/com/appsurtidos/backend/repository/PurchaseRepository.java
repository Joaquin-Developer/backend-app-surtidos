package com.appsurtidos.backend.repository;

import com.appsurtidos.backend.models.Purchase;
import com.appsurtidos.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> findByUser(User user);
}
