package com.appsurtidos.backend.dto;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class AllProductDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDateTime scrapingDate;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String ean;

    private String name;

    private String imageLink;

    private String currency;

    private BigDecimal price;

    private Long supermarketChainId;
}
