package com.appsurtidos.backend.model;

public class ScrapperProduct {
    private String ean;
    private String name;
    private String price;
    private String imageUrl;

    public ScrapperProduct() {}

    public ScrapperProduct(String ean, String name, String price, String imageUrl) {
        this.ean = ean;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
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

    @Override
    public String toString() {
        return "EAN: " + ean + ", NAME: " + name + ", PRICE: " + price;
    }

}

