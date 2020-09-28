package com.example.auctionapp.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "category")
public class Category extends Resource {

    @NotBlank
    private String name;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
