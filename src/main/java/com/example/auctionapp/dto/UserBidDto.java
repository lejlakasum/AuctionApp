package com.example.auctionapp.dto;

public class UserBidDto {

    private Long productId;

    private String name;

    private Long auctionEndDate;

    private Double userBid;

    private Double highestBid;

    private Integer numberOfBids;

    private String imgUrl;

    public UserBidDto(Long productId,
                      String name,
                      Long auctionEndDate,
                      Double userBid,
                      Double highestBid,
                      Integer numberOfBids,
                      String imgUrl) {
        this.productId = productId;
        this.name = name;
        this.auctionEndDate = auctionEndDate;
        this.userBid = userBid;
        this.highestBid = highestBid;
        this.numberOfBids = numberOfBids;
        this.imgUrl = imgUrl;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAuctionEndDate() {
        return auctionEndDate;
    }

    public void setAuctionEndDate(Long auctionEndDate) {
        this.auctionEndDate = auctionEndDate;
    }

    public Double getUserBid() {
        return userBid;
    }

    public void setUserBid(Double userBid) {
        this.userBid = userBid;
    }

    public Double getHighestBid() {
        return highestBid;
    }

    public void setHighestBid(Double highestBid) {
        this.highestBid = highestBid;
    }

    public Integer getNumberOfBids() {
        return numberOfBids;
    }

    public void setNumberOfBids(Integer numberOfBids) {
        this.numberOfBids = numberOfBids;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
