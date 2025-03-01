package com.appsurtidos.backend.dto;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class AllProductDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDateTime scrapingDate;

    // default null
    private Long ean;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String imageLink;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String currency;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BigDecimal price;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long supermarketChainId;
}
