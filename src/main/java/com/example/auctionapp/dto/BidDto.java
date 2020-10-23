package com.example.auctionapp.dto;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class BidDto extends BaseResourceDto {

    @NotNull
    private Long userId;

    private String userName;

    @NotNull
    private Long productId;

    private String productName;

    @NotNull
    private LocalDateTime bidTime;

    @NotNull
    private Double bidAmount;

    public BidDto() {
    }

    public BidDto(Long id,
                  LocalDateTime dateCreated,
                  LocalDateTime lastModifiedDate,
                  Long userId, String userName,
                  Long productId, String productName,
                  LocalDateTime bidTime,
                  Double bidAmount) {
        super(id, dateCreated, lastModifiedDate);
        this.userId = userId;
        this.userName = userName;
        this.productId = productId;
        this.productName = productName;
        this.bidTime = bidTime;
        this.bidAmount = bidAmount;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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
