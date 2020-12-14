package com.example.auctionapp.Util;

import com.example.auctionapp.dto.*;
import com.example.auctionapp.dto.UserDtos.AddressDto;
import com.example.auctionapp.dto.UserDtos.CardInformationDto;
import com.example.auctionapp.dto.UserDtos.CityDto;
import com.example.auctionapp.dto.UserDtos.CountryDto;
import com.example.auctionapp.dto.UserDtos.UserAccountDto;
import com.example.auctionapp.dto.UserDtos.UserDetailsDto;
import com.example.auctionapp.dto.UserDtos.UserRegisterDto;
import com.example.auctionapp.model.*;

import java.time.LocalDateTime;
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
                bid.getUser().getUserLoginInformation().getFirstName() + " " + bid.getUser().getUserLoginInformation().getLastName(),
                bid.getUser().getUserLoginInformation().getImage().getUrl(),
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

        List<SubcategoryDto> subcategories = new ArrayList<>();

        if(category.getSubcategories() != null) {
            subcategories = category.getSubcategories().stream().map(subcategory -> {
                return mapSubcategoryToDto(subcategory);
            })
                    .collect(Collectors.toList());
        }

        return new CategoryDto(
                category.getId(),
                category.getDateCreated(),
                category.getLastModifiedDate(),
                category.getName(),
                category.getImage().getUrl(),
                subcategories
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

    public static AddressDto mapAddressToAddressDto(Address address) {

        if(address == null) {
            return new AddressDto();
        }

        return new AddressDto(
                address.getId(),
                address.getDateCreated(),
                address.getLastModifiedDate(),
                address.getStreet(),
                address.getState(),
                address.getZipCode(),
                mapCityToCityDto(address.getCity())
                );
    }

    public static CardInformationDto mapCardToCardDto(CardInformation cardInformation) {

        if(cardInformation == null) {
            return new CardInformationDto();
        }

        return new CardInformationDto(
                cardInformation.getId(),
                cardInformation.getDateCreated(),
                cardInformation.getLastModifiedDate(),
                cardInformation.getNameOnCard(),
                cardInformation.getCardNumber(),
                cardInformation.getYearExpiration(),
                cardInformation.getMonthExpiration(),
                cardInformation.getCvc(),
                cardInformation.getPaypal(),
                cardInformation.getCreditCard()
        );
    }

    public static UserRegisterDto mapUserRegisterToUserRegisterDto(UserRegisterInformation registerInformation) {

        return new UserRegisterDto(
                registerInformation.getId(),
                registerInformation.getDateCreated(),
                registerInformation.getLastModifiedDate(),
                registerInformation.getFirstName(),
                registerInformation.getLastName(),
                registerInformation.getEmail(),
                registerInformation.getRole().getId(),
                registerInformation.getImage().getUrl()
        );
    }

    public static UserDetailsDto mapUserDetailsToUserDetailsDto(UserDetails userDetails) {

        if(userDetails == null) {
            return new UserDetailsDto();
        }

        Long birthDate = TimeUtility.LocalDateTimeToTimestamp(LocalDateTime.of(1900,1,1,0,0));

        if(userDetails.getBirthDate()!=null) {
            birthDate = TimeUtility.LocalDateTimeToTimestamp(userDetails.getBirthDate());
        }

        String gender = "";
        if(userDetails.getGender() != null) {
            gender = userDetails.getGender().label;
        }

        return new UserDetailsDto(
                userDetails.getId(),
                userDetails.getDateCreated(),
                userDetails.getLastModifiedDate(),
                userDetails.getPhoneNumber(),
                birthDate,
                gender,
                mapAddressToAddressDto(userDetails.getAddress()),
                mapCardToCardDto(userDetails.getCardInformation())
        );
    }

    public static UserAccountDto mapUserToUserDto(UserAccount userAccount) {

        return new UserAccountDto(userAccount.getId(),
                userAccount.getDateCreated(),
                userAccount.getLastModifiedDate(),
                mapUserRegisterToUserRegisterDto(userAccount.getUserLoginInformation()),
                mapUserDetailsToUserDetailsDto(userAccount.getUserDetails())
        );
    }

    public static UserBidDto mapBidToUserBidDto(Bid bid) {
        Double highestBid = bid.getProduct().getBids().stream().mapToDouble(b -> b.getBidAmount()).max().getAsDouble();

        return new UserBidDto(
                bid.getProduct().getId(),
                bid.getProduct().getName(),
                TimeUtility.LocalDateTimeToTimestamp(bid.getProduct().getAuctionEndDate()),
                bid.getBidAmount(),
                highestBid,
                bid.getProduct().getBids().size(),
                bid.getProduct().getImages().get(0).getUrl()
        );
    }

    public static CountryDto mapCountryToCountryDto(Country country) {

        List<String> cities = new ArrayList<>();

        if(country.getCities() != null) {
            cities = country.getCities().stream().map(city -> {
                return city.getName();
            })
                    .collect(Collectors.toList());
        }
        return new CountryDto(
                country.getId(),
                country.getDateCreated(),
                country.getLastModifiedDate(),
                country.getName(),
                cities);
    }

    public static CityDto mapCityToCityDto(City city) {

        if(city == null) {
            return null;
        }

        return new CityDto(
                city.getId(),
                city.getDateCreated(),
                city.getLastModifiedDate(),
                city.getName(),
                city.getCountry().getId(),
                city.getCountry().getName()
        );
    }

    public static UserBidDto mapProductToUserBidDto(Product product) {
        Double highestBid = 0.;
        if(product.getBids().size() > 0) {
            highestBid = product.getBids().stream().mapToDouble(b -> b.getBidAmount()).max().getAsDouble();
        }

        return new UserBidDto(
                product.getId(),
                product.getName(),
                TimeUtility.LocalDateTimeToTimestamp(product.getAuctionEndDate()),
                product.getPrice(),
                highestBid,
                product.getBids().size(),
                product.getImages().get(0).getUrl()
        );
    }
}
