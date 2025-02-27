package com.appsurtidos.backend.repository;

import com.appsurtidos.backend.models.SupermarketChain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface SupermarketChainRepository extends JpaRepository<SupermarketChain, Long> {
    Optional<SupermarketChain> findByName(String name);
}
