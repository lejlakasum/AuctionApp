package com.example.auctionapp.dto;

import com.example.auctionapp.customValidator.ColorConstraint;
import com.example.auctionapp.customValidator.SizeConstraint;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class SearchRequest {

    @NotNull
    private String name;

    @NotNull
    private Long subcategoryId;

    @NotNull
    @ColorConstraint
    private String color;

    @NotNull
    @SizeConstraint
    private String size;

    @NotNull
    @Min(-1)
    private Double minPrice;

    @NotNull
    @Min(-1)
    private Double maxPrice;

    @NotNull
    private String order;

    @Min(1)
    private Integer pageNumber;

    @Min(1)
    private Integer pageSize;

    public SearchRequest(String name,
                         Long subcategoryId,
                         String color,
                         String size,
                         Double minPrice,
                         Double maxPrice,
                         String order,
                         Integer pageNumber,
                         Integer pageSize) {
        this.name = name;
        this.subcategoryId = subcategoryId;
        this.color = color;
        this.size = size;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.order = order;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
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

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
