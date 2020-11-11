package com.example.auctionapp.dto.FilterDto;

import javax.persistence.Tuple;
import java.util.List;

public class FilterPriceDto {

    private Double minPrice;

    private Double maxPrice;

    private Double avgPrice;

    List<Tuple> pricesCount;

    public FilterPriceDto(Double minPrice, Double maxPrice, Double avgPrice, List<Tuple> pricesCount) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.avgPrice = avgPrice;
        this.pricesCount = pricesCount;
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

    public Double getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(Double avgPrice) {
        this.avgPrice = avgPrice;
    }

    public List<Tuple> getPricesCount() {
        return pricesCount;
    }

    public void setPricesCount(List<Tuple> pricesCount) {
        this.pricesCount = pricesCount;
    }
}
