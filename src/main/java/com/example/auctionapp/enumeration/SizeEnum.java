package com.example.auctionapp.enumeration;

import com.example.auctionapp.exception.BadRequestException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
            if(value.getLabel().equals(size)) {
                return value;
            }
        }
        throw new BadRequestException("Size does not exist");
    }

    public static List<String> getSizes() {
        return Arrays.stream(SizeEnum.values()).map(name -> {
            return name.getLabel();
        }).collect(Collectors.toList());
    }

    public String getLabel() {
        return label;
    }
}
