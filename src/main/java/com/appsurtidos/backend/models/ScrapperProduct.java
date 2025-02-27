package com.appsurtidos.backend.models;

public class ScrapperProduct {
    private String scrapingDate;
    private String ean;
    private String name;
    private String imageUrl;
    private String currency;
    private String price;
    private String supermarketChain;

    public ScrapperProduct() {}

    public ScrapperProduct(String scrapingDate, String ean, String name, String imageUrl, String currency, String price, String supermarketChain) {
        this.scrapingDate = scrapingDate;
        this.ean = ean;
        this.name = name;
        this.imageUrl = imageUrl;
        this.currency = currency;
        this.price = price;
        this.supermarketChain = supermarketChain;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getSupermarketChain() {
        return supermarketChain;
    }

    public void setSupermarketChain(String supermarketChain) {
        this.supermarketChain = supermarketChain;
    }

    public String getScrapingDate() {
        return scrapingDate;
    }

    public void setScrapingDate(String scrapingDate) {
        this.scrapingDate = scrapingDate;
    }

    @Override
    public String toString() {
        return "EAN: " + ean + ", NAME: " + name + ", PRICE: " + price;
    }

}

