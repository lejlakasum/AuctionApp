package com.example.auctionapp.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class RoleDto extends BaseResourceDto {

    @NotBlank
    private String name;

    public RoleDto() {
    }

    public RoleDto(Long id, LocalDateTime dateCreated, LocalDateTime lastModifiedDate, String name) {
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
