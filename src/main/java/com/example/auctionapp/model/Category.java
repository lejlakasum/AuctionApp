package com.example.auctionapp.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "category")
public class Category extends Resource {

    @NotBlank
    private String name;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "image_id", referencedColumnName = "id", updatable = false)
    private Image image;

    @OneToMany(mappedBy = "category")
    private List<Subcategory> subcategories;

    public Category() {
    }

    public Category(String name, Image image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public List<Subcategory> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<Subcategory> subcategories) {
        this.subcategories = subcategories;
    }
}
