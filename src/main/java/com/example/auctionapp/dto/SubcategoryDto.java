package com.example.auctionapp.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class SubcategoryDto extends BaseResourceDto {

    @NotBlank
    private String name;

    @NotNull
    private Long categoryId;

    public SubcategoryDto() {
    }

    public SubcategoryDto(Long id, LocalDateTime dateCreated, LocalDateTime lastModifiedDate, String name, Long categoryId) {
        super(id, dateCreated, lastModifiedDate);
        this.name = name;
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
