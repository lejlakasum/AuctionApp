package com.example.auctionapp.dto.UserDtos;

import com.example.auctionapp.dto.BaseResourceDto;

import java.time.LocalDateTime;

public class AddressDto extends BaseResourceDto {

    private String street;

    private String state;

    private String zipCode;

    private CityDto city;

    public AddressDto() {
    }

    public AddressDto(Long id,
                      LocalDateTime dateCreated,
                      LocalDateTime lastModifiedDate,
                      String street,
                      String state,
                      String zipCode,
                      CityDto city) {
        super(id, dateCreated, lastModifiedDate);
        this.street = street;
        this.state = state;
        this.zipCode = zipCode;
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public CityDto getCity() {
        return city;
    }

    public void setCity(CityDto city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
