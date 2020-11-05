package com.example.auctionapp.dto;

public class SearchRequest {

    private String name;

    private Long subcategoryId;

    private String color;

    private String size;

    private Double minPrice;

    private Double maxPrice;

    private String order;

    private Integer pageNumber;

    public SearchRequest(String name,
                         Long subcategoryId,
                         String color,
                         String size,
                         Double minPrice,
                         Double maxPrice,
                         String order,
                         Integer pageNumber) {
        this.name = name;
        this.subcategoryId = subcategoryId;
        this.color = color;
        this.size = size;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.order = order;
        this.pageNumber = pageNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSubcategoryId() {
        return subcategoryId;
    }

    public void setSubcategoryId(Long subcategoryId) {
        this.subcategoryId = subcategoryId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }
}
