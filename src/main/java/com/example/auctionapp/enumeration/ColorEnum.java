package com.example.auctionapp.enumeration;

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
        return ColorEnum.valueOf(color.toUpperCase());
    }
}
