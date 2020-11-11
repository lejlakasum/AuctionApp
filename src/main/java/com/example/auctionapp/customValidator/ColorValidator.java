package com.example.auctionapp.customValidator;

import com.example.auctionapp.enumeration.ColorEnum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class ColorValidator implements ConstraintValidator<ColorConstraint, String> {

    @Override
    public void initialize(ColorConstraint color) {
    }

    @Override
    public boolean isValid(String colorField, ConstraintValidatorContext cxt) {

        try {
            if(!colorField.isEmpty()) {
                ColorEnum.fromValue(colorField);
            }
        }
        catch (Exception ex) {
            return false;
        }

        return true;
    }

}

