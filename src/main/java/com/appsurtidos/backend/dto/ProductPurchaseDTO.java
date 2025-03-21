package com.appsurtidos.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductPurchaseDTO {
    private Long productIdScraper;  // if the product exists in scrapper data.
    private String productManualName; // if the product is entered manually
    private Integer quantity;
    private String currency;
    private BigDecimal price;

    public ProductPurchaseDTO() {}
}
