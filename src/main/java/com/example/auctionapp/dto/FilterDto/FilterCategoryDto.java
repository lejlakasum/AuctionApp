package com.example.auctionapp.dto.FilterDto;

import java.util.List;

public class FilterCategoryDto {

    private Long id;

    private String name;

    private List<FilterSubcategoryDto> subcategories;

    public FilterCategoryDto(Long id, String name, List<FilterSubcategoryDto> subcategories) {
        this.id = id;
        this.name = name;
        this.subcategories = subcategories;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FilterSubcategoryDto> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<FilterSubcategoryDto> subcategories) {
        this.subcategories = subcategories;
    }
}
