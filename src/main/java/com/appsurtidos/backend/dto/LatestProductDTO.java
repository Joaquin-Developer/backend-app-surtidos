package com.appsurtidos.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class LatestProductDTO {
    private LocalDateTime scrapingDate;
    private String supermarket;
    private Long ean;
    private String name;
    private String price;
}
