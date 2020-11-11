package com.example.auctionapp.controller;

import com.example.auctionapp.dto.FilterDto.FiltersResponseDto;
import com.example.auctionapp.dto.ProductDto;
import com.example.auctionapp.dto.SearchRequest;
import com.example.auctionapp.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

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

    @PostMapping()
    @Valid
    public ResponseEntity<List<ProductDto>> searchProducts(@Valid @RequestBody SearchRequest searchRequest) {
        return new ResponseEntity<>(shopService.searchProducts(searchRequest), HttpStatus.OK);
    }
}
