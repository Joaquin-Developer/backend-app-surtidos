package com.appsurtidos.backend.models;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Embeddable
public class AllProductsId implements Serializable {

    private LocalDateTime scrapingDate;
    private String ean;

    public AllProductsId() {}

    public AllProductsId(LocalDateTime scrapingDate, String ean) {
        this.scrapingDate = scrapingDate;
        this.ean = ean;
    }

    public LocalDateTime getScrapingDate() {
        return scrapingDate;
    }

    public void setScrapingDate(LocalDateTime scrapingDate) {
        this.scrapingDate = scrapingDate;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AllProductsId that = (AllProductsId) o;
        return Objects.equals(scrapingDate, that.scrapingDate) &&
                Objects.equals(ean, that.ean);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scrapingDate, ean);
    }
}
