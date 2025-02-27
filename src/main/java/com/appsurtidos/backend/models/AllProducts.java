package com.appsurtidos.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Entity
@Table(name = "all_products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AllProducts {
    @EmbeddedId
    private AllProductsId id;

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

//    public AllProducts() {}
//
//    public AllProducts(AllProductsId id, String name, String imageLink, String currency, BigDecimal price, SupermarketChain supermarketChain) {
//        this.id = id;
//        this.name = name;
//        this.imageLink = imageLink;
//        this.currency = currency;
//        this.price = price;
//        this.supermarketChain = supermarketChain;
//    }
}
