package com.example.auctionapp.Util;

import com.example.auctionapp.enumeration.ColorEnum;
import com.example.auctionapp.enumeration.SizeEnum;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EnumUtility {

    public static List<String> getColors() {

        return Arrays.stream(ColorEnum.values()).map(name -> {
            return name.toString();
        }).collect(Collectors.toList());
    }

    public static List<String> getSizes() {
        return Arrays.stream(SizeEnum.values()).map(name -> {
            if(name.toString().contains("_")) {
                return name.toString().replace("_", " ");
            }
            return name.toString();
        }).collect(Collectors.toList());
    }
}
