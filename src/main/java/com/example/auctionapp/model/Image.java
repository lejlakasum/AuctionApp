package com.example.auctionapp.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "image")
public class Image extends Resource {

    @NotBlank
    private String url;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", updatable = false)
    private Product product;

    public Image() {
    }

    public Image(String url, Product product) {
        this.url = url;
        this.product = product;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
