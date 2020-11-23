package com.example.auctionapp.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "bid")
public class Bid extends Resource {

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", updatable = false)
    private UserAccount userAccount;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", updatable = false)
    private Product product;

    @NotNull
    private LocalDateTime bidTime;

    @NotNull
    private Double bidAmount;

    public Bid() {
    }

    public Bid(UserAccount userAccount, Product product, LocalDateTime bidTime, Double bidAmount) {
        this.userAccount = userAccount;
        this.product = product;
        this.bidTime = bidTime;
        this.bidAmount = bidAmount;
    }

    public UserAccount getUser() {
        return userAccount;
    }

    public void setUser(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public LocalDateTime getBidTime() {
        return bidTime;
    }

    public void setBidTime(LocalDateTime bidTime) {
        this.bidTime = bidTime;
    }

    public Double getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(Double bidAmount) {
        this.bidAmount = bidAmount;
    }
}
