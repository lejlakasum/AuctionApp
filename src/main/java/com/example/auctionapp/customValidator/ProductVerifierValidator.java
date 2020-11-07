package com.example.auctionapp.customValidator;

import com.example.auctionapp.dto.ProductDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ProductVerifierValidator implements ConstraintValidator<ProductVerifier, ProductDto> {


    public boolean isValid(ProductDto value,
                           ConstraintValidatorContext context) {

        if(value.getAuctionStartDate() > value.getAuctionEndDate()) {
            return false;
        }

        return true;
    }
}
