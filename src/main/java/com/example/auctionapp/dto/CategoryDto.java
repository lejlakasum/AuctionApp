package com.example.auctionapp.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public class CategoryDto extends BaseResourceDto {

    @NotNull
    private String name;

    @NotBlank
    private String imageUrl;

    private List<SubcategoryDto> subcategories;

    public CategoryDto() {
    }

    public CategoryDto(Long id,
                       LocalDateTime dateCreated,
                       LocalDateTime lastModifiedDate,
                       String name,
                       String imageUrl,
                       List<SubcategoryDto> subcategories) {
        super(id, dateCreated, lastModifiedDate);
        this.name = name;
        this.imageUrl = imageUrl;
        this.subcategories = subcategories;
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

    public List<SubcategoryDto> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<SubcategoryDto> subcategories) {
        this.subcategories = subcategories;
    }
}
