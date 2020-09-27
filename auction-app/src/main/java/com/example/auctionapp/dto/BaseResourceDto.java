package com.example.auctionapp.dto;

import java.time.LocalDateTime;

public class BaseResourceDto {

    private Long id;

    private LocalDateTime dateCreated;

    private LocalDateTime lastModifiedDate;

    public BaseResourceDto() {
    }

    public BaseResourceDto(Long id, LocalDateTime dateCreated, LocalDateTime lastModifiedDate) {
        this.id = id;
        this.dateCreated = dateCreated;
        this.lastModifiedDate = lastModifiedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
