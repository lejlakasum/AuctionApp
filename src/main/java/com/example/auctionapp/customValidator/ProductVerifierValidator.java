package com.example.auctionapp.customValidator;

import com.example.auctionapp.Util.EnumUtility;
import com.example.auctionapp.dto.ProductDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.stream.Collectors;

public class ProductVerifierValidator implements ConstraintValidator<ProductVerifier, ProductDto> {


    public boolean isValid(ProductDto value,
                           ConstraintValidatorContext context) {

        if(value.getAuctionStartDate() > value.getAuctionEndDate()) {
            return false;
        }

        List<String> colors = EnumUtility.getColors()
                                         .stream()
                                         .map(color -> {return color.toLowerCase();})
                                         .collect(Collectors.toList());

        if(!colors.contains(value.getColor().toLowerCase())) {
            return false;
        }

        List<String> sizes = EnumUtility.getSizes()
                                        .stream()
                                        .map(size -> {return size.toLowerCase();})
                                        .collect(Collectors.toList());

        if(!sizes.contains(value.getSize().toLowerCase())) {
            return false;
        }


        return true;
    }
}
