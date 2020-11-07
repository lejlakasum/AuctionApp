package com.example.auctionapp.enumeration;

import com.example.auctionapp.exception.BadRequestException;

public enum ColorEnum {
    BLACK ("Black"),
    WHITE ("White"),
    GREY ("Grey"),
    RED ("Red"),
    YELLOW ("Yellow"),
    BLUE ("Blue"),
    PURPLE ("Purple"),
    GREEN ("Green"),
    PINK ("Pink"),
    ORANGE ("Orange"),
    BROWN ("Brown");

    public final String label;

    private ColorEnum(String label) {
        this.label = label;
    }

    public static ColorEnum fromValue(String color) {
        for (ColorEnum value : ColorEnum.values()) {
            if(value.label.equals(color)) {
                return value;
            }
        }
        throw new BadRequestException("Color does not exist");
    }
}
