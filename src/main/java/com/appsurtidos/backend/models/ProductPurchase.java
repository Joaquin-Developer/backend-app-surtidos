package com.appsurtidos.backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "products_purchases")
@Getter
@Setter
public class ProductPurchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "_products_purchases_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "purchase_id", nullable = false)
    private Purchase purchase;

    @Column(name = "product_id_scraper", nullable = true)
    private Long productIdScraper;

    @Column(name = "product_manual_name", nullable = true, length = 200)
    private String productManualName;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "currency", nullable = false, length = 10)
    private String currency;

    @Column(name = "price", nullable = false)
    private BigDecimal price;
}
