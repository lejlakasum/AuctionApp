package com.example.auctionapp.dto.FilterDto;

import java.util.List;

public class FiltersResponseDto {

    private List<String> colors;

    private List<String> sizes;

    private List<FilterCategoryDto> categories;

    private FilterPriceDto prices;

    public FiltersResponseDto() {
    }

    public FiltersResponseDto(List<String> colors, List<String> sizes, List<FilterCategoryDto> categories, FilterPriceDto prices) {
        this.colors = colors;
        this.sizes = sizes;
        this.categories = categories;
        this.prices = prices;
    }

    public List<String> getColors() {
        return colors;
    }

    public void setColors(List<String> colors) {
        this.colors = colors;
    }

    public List<String> getSizes() {
        return sizes;
    }

    public void setSizes(List<String> sizes) {
        this.sizes = sizes;
    }

    public List<FilterCategoryDto> getCategories() {
        return categories;
    }

    public void setCategories(List<FilterCategoryDto> categories) {
        this.categories = categories;
    }

    public FilterPriceDto getPrices() {
        return prices;
    }

    public void setPrices(FilterPriceDto prices) {
        this.prices = prices;
    }
}
