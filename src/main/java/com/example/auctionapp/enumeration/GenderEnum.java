package com.example.auctionapp.enumeration;

import com.example.auctionapp.exception.BadRequestException;

public enum GenderEnum {
    MALE ("Male"),
    FEMALE ("Female"),
    OTHER ("Other");

    public final String label;

    private GenderEnum(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static GenderEnum fromValue(String gender) {

        for (GenderEnum value : GenderEnum.values()) {
            if(value.getLabel().equals(gender)) {
                return value;
            }
        }
        throw new BadRequestException("Gender does not exist");
    }

}
