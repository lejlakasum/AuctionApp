package com.example.auctionapp.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class ImageDto extends BaseResourceDto {

    @NotBlank
    private String url;

    public ImageDto() {
    }

    public ImageDto(Long id, LocalDateTime dateCreated, LocalDateTime lastModifiedDate, String url) {
        super(id, dateCreated, lastModifiedDate);
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
