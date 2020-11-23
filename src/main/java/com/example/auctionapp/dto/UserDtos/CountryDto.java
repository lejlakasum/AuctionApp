package com.example.auctionapp.dto.UserDtos;

import com.example.auctionapp.dto.BaseResourceDto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.List;

public class CountryDto extends BaseResourceDto {

    @NotEmpty
    @Pattern(regexp = "^[A-Za-z\\s\\-]*$")
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
