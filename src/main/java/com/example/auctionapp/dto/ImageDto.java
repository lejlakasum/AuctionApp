package com.example.auctionapp.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class ImageDto extends BaseResourceDto {

    @NotBlank
    private String url;

    @NotNull
    private Long productId;

    public ImageDto() {
    }

    public ImageDto(Long id, LocalDateTime dateCreated, LocalDateTime lastModifiedDate, String url, Long productId) {
        super(id, dateCreated, lastModifiedDate);
        this.url = url;
        this.productId = productId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
