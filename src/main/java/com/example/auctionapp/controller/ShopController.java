package com.example.auctionapp.controller;

import com.example.auctionapp.dto.FilterDto.FiltersResponseDto;
import com.example.auctionapp.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shop")
public class ShopController {

    private final ShopService shopService;

    @Autowired
    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping("/filters")
    public ResponseEntity<FiltersResponseDto> getShopFilters() {
        return new ResponseEntity<>(shopService.getShopFilters(), HttpStatus.OK);
    }
}
