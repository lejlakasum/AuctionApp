package com.example.auctionapp.dto.UserDtos;

import com.example.auctionapp.dto.BaseResourceDto;

import java.time.LocalDateTime;

public class CityDto extends BaseResourceDto {

    private String name;

    private Long countryId;

    private String countryName;

    public CityDto(Long id,
                   LocalDateTime dateCreated,
                   LocalDateTime lastModifiedDate,
                   String name,
                   Long countryId,
                   String countryName) {
        super(id, dateCreated, lastModifiedDate);
        this.name = name;
        this.countryId = countryId;
        this.countryName = countryName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
