package com.example.auctionapp.model;

import org.aspectj.lang.annotation.Before;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product extends Resource {

    @NotBlank
    private String name;

    private String description;

    @NotNull
    private Double price;

    @ManyToOne
    @JoinColumn(name = "subcategory_id", referencedColumnName = "id", updatable = false)
    private Subcategory subcategory;

    private LocalDateTime auctionStartDate;

    private LocalDateTime auctionEndDate;

    @OneToMany(mappedBy = "product")
    private Set<Image> images;

    public Product() {
    }

    public Product(String name,
                   String description,
                   Double price,
                   Subcategory subcategory,
                   LocalDateTime auctionStartDate,
                   LocalDateTime auctionEndDate,
                   Set<Image> images) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.subcategory = subcategory;
        this.auctionStartDate = auctionStartDate;
        this.auctionEndDate = auctionEndDate;
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Subcategory getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(Subcategory subcategory) {
        this.subcategory = subcategory;
    }

    public LocalDateTime getAuctionStartDate() {
        return auctionStartDate;
    }

    public void setAuctionStartDate(LocalDateTime auctionStartDate) {
        this.auctionStartDate = auctionStartDate;
    }

    public LocalDateTime getAuctionEndDate() {
        return auctionEndDate;
    }

    public void setAuctionEndDate(LocalDateTime auctionEndDate) {
        this.auctionEndDate = auctionEndDate;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }
}
