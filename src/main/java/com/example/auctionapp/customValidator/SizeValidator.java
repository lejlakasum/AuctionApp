package com.example.auctionapp.customValidator;

import com.example.auctionapp.enumeration.SizeEnum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SizeValidator implements ConstraintValidator<SizeConstraint, String> {

    @Override
    public void initialize(SizeConstraint size) {
    }

    @Override
    public boolean isValid(String sizeField, ConstraintValidatorContext cxt) {

        try {
            if(!sizeField.isEmpty()) {
                SizeEnum.fromValue(sizeField);
            }
        }
        catch (Exception ex) {
            return false;
        }

        return true;
    }
}
