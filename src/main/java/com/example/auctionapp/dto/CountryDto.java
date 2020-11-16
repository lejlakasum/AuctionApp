package com.example.auctionapp.dto;

import java.time.LocalDateTime;
import java.util.List;

public class CountryDto extends BaseResourceDto {

    private String name;

    private List<String> cities;

    public CountryDto(Long id, LocalDateTime dateCreated, LocalDateTime lastModifiedDate, String name, List<String> cities) {
        super(id, dateCreated, lastModifiedDate);
        this.name = name;
        this.cities = cities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCities() {
        return cities;
    }

    public void setCities(List<String> cities) {
        this.cities = cities;
    }
}
