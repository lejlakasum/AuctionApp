package com.example.auctionapp.dto;

import java.time.LocalDate;

public class BaseResourceDto {

    private Long id;

    private LocalDate dateCreated;

    private LocalDate lastModifiedDate;

    public BaseResourceDto() {
    }

    public BaseResourceDto(Long id, LocalDate dateCreated, LocalDate lastModifiedDate) {
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

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDate getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDate lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
