package com.example.auctionapp.dto;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class CategoryDto extends BaseResourceDto {

    @NotNull
    private String name;

    public CategoryDto() {
    }

    public CategoryDto(Long id, LocalDateTime dateCreated, LocalDateTime lastModifiedDate, @NotNull String name) {
        super(id, dateCreated, lastModifiedDate);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
