package com.appsurtidos.backend.dto;

import com.appsurtidos.backend.models.ProductPurchase;
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

    public static ProductPurchaseDTO fromEntity(ProductPurchase productPurchase) {
        ProductPurchaseDTO productPurchaseDTO = new ProductPurchaseDTO();
        productPurchaseDTO.setProductIdScraper(productPurchase.getProductIdScraper());
        productPurchaseDTO.setProductManualName(productPurchase.getProductManualName());
        productPurchaseDTO.setQuantity(productPurchase.getQuantity());
        productPurchaseDTO.setCurrency(productPurchase.getCurrency());
        productPurchaseDTO.setPrice(productPurchase.getPrice());
        return productPurchaseDTO;
    }
}
