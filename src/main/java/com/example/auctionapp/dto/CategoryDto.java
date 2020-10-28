package com.example.auctionapp.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class CategoryDto extends BaseResourceDto {

    @NotNull
    private String name;

    @NotBlank
    private String imageUrl;

    public CategoryDto() {
    }

    public CategoryDto(Long id, LocalDateTime dateCreated, LocalDateTime lastModifiedDate, String name, String imageUrl) {
        super(id, dateCreated, lastModifiedDate);
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
