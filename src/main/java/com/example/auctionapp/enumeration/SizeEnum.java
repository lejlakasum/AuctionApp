package com.example.auctionapp.enumeration;

import com.example.auctionapp.exception.BadRequestException;

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

        for (SizeEnum value : SizeEnum.values()) {
            if(value.label.equals(size)) {
                return value;
            }
        }
        throw new BadRequestException("Size does not exist");
    }
}
