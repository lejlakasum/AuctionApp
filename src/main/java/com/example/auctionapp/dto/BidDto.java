package com.example.auctionapp.dto;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class BidDto extends BaseResourceDto {

    @NotNull
    private Long userId;

    private String userName;

    private String userImage;

    @NotNull
    private Long productId;

    private String productName;

    @NotNull
    private Long bidTime;

    @NotNull
    private Double bidAmount;

    public BidDto() {
    }

    public BidDto(Long id,
                  LocalDateTime dateCreated,
                  LocalDateTime lastModifiedDate,
                  Long userId,
                  String userName,
                  String userImage,
                  Long productId,
                  String productName,
                  Long bidTime,
                  Double bidAmount) {
        super(id, dateCreated, lastModifiedDate);
        this.userId = userId;
        this.userName = userName;
        this.userImage = userImage;
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

    public Long getBidTime() {
        return bidTime;
    }

    public void setBidTime(Long bidTime) {
        this.bidTime = bidTime;
    }

    public Double getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(Double bidAmount) {
        this.bidAmount = bidAmount;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }
}
