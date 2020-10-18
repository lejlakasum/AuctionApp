package com.example.auctionapp.dto;

public class CollectionDto {

    private CategoryDto category;

    private Double lowestPrice;

    public CollectionDto() {
    }

    public CollectionDto(CategoryDto category, Double lowestPrice) {
        this.category = category;
        this.lowestPrice = lowestPrice;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }

    public Double getLowestPrice() {
        return lowestPrice;
    }

    public void setLowestPrice(Double lowestPrice) {
        this.lowestPrice = lowestPrice;
    }
}
