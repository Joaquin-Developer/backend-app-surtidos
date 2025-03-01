package com.appsurtidos.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "all_products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AllProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduct;

    @Column(nullable = false)
    private LocalDateTime scrapingDate;

    @Column(nullable = true)
    private Long ean;

    @Column(nullable = false, length = 200)
    private String name;

    @Column(length = 250)
    private String imageLink;

    @Column(nullable = false, length = 10)
    private String currency;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "supermarket_chain_id", nullable = false)
    private SupermarketChain supermarketChain;
}
