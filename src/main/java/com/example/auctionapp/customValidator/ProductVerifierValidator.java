package com.example.auctionapp.customValidator;

import com.example.auctionapp.Util.EnumUtility;
import com.example.auctionapp.dto.ProductDto;
import com.example.auctionapp.enumeration.ColorEnum;
import com.example.auctionapp.enumeration.SizeEnum;

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

        return true;
    }
}
