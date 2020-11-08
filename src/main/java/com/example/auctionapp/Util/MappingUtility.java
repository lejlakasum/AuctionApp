package com.example.auctionapp.Util;

import com.example.auctionapp.dto.BidDto;
import com.example.auctionapp.dto.CategoryDto;
import com.example.auctionapp.dto.ImageDto;
import com.example.auctionapp.dto.ProductDto;
import com.example.auctionapp.dto.SubcategoryDto;
import com.example.auctionapp.dto.UserDto;
import com.example.auctionapp.model.Bid;
import com.example.auctionapp.model.Category;
import com.example.auctionapp.model.Image;
import com.example.auctionapp.model.Product;
import com.example.auctionapp.model.Subcategory;
import com.example.auctionapp.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MappingUtility {

    public static BidDto mapBidToBidDto(Bid bid) {

        return new BidDto(
                bid.getId(),
                bid.getDateCreated(),
                bid.getLastModifiedDate(),
                bid.getUser().getId(),
                bid.getUser().getFirstName() + " " + bid.getUser().getLastName(),
                bid.getUser().getImage().getUrl(),
                bid.getProduct().getId(),
                bid.getProduct().getName(),
                TimeUtility.LocalDateTimeToTimestamp(bid.getBidTime()),
                bid.getBidAmount()
        );
    }

    public static ProductDto mapProductToProductDto(Product product) {

        List<String> images = product.getImages().stream().map(
                     image -> {return image.getUrl();
                     }
            ).collect(Collectors.toList());

        List<BidDto> bids = new ArrayList<>();
        if(product.getBids()!=null) {
            bids = product.getBids().stream().map(
                    bid -> {
                        return mapBidToBidDto(bid);
                    }
            ).collect(Collectors.toList());
        }
        return new ProductDto(product.getId(),
                product.getDateCreated(),
                product.getLastModifiedDate(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getSubcategory().getId(),
                TimeUtility.LocalDateTimeToTimestamp(product.getAuctionStartDate()),
                TimeUtility.LocalDateTimeToTimestamp(product.getAuctionEndDate()),
                images,
                product.getFeature(),
                product.getUser().getId(),
                bids,
                product.getColor().getLabel(),
                product.getSize().getLabel()
        );

    }

    public static List<ProductDto> mapProductListToDtoList(List<Product> products) {
        return products.stream().map(
                product -> {return mapProductToProductDto(product);
                }
        ).collect(Collectors.toList());
    }

    public static CategoryDto mapCategoryToCategoryDto(Category category) {

        return new CategoryDto(
                category.getId(),
                category.getDateCreated(),
                category.getLastModifiedDate(),
                category.getName(),
                category.getImage().getUrl()
        );
    }

    public static ImageDto mapImageToImageDto(Image image) {
        return new ImageDto(image.getId(),
                image.getDateCreated(),
                image.getLastModifiedDate(),
                image.getUrl()
        );
    }

    public static SubcategoryDto mapSubcategoryToDto(Subcategory subcategory) {
        return new SubcategoryDto(subcategory.getId(),
                subcategory.getDateCreated(),
                subcategory.getLastModifiedDate(),
                subcategory.getName(),
                subcategory.getCategory().getId()
        );
    }

    public static UserDto mapUserToUserDto(User user) {

        return new UserDto(user.getId(),
                user.getDateCreated(),
                user.getLastModifiedDate(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getRole().getId(),
                user.getImage().getUrl()
        );
    }
}
