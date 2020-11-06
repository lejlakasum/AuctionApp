package com.example.auctionapp.enumeration;

public enum SizeEnum {
    EXTRA_SMALL ("Extra Small"),
    SMALL ("Small"),
    MEDIUM ("Medium"),
    LARGE ("Large"),
    EXTRA_LARGE ("Extra Large");

    public final String label;

    private SizeEnum(String label) {
        this.label = label;
    }

    public static SizeEnum fromValue(String size) {

            if(size.contains(" ")) {
                size = size.replace(" ", "_");
            }
        return SizeEnum.valueOf(size.toUpperCase());
    }
}
