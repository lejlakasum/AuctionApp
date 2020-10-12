package com.example.auctionapp.dto;

import java.util.List;

public class CollectionDto {

    private String categoryName;

    private List<ProductDto> products;

    public CollectionDto() {
    }

    public CollectionDto(String categoryName, List<ProductDto> products) {
        this.categoryName = categoryName;
        this.products = products;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }
}
