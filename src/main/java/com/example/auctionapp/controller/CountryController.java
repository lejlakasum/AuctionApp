package com.example.auctionapp.controller;

import com.example.auctionapp.dto.CategoryDto;
import com.example.auctionapp.dto.CountryDto;
import com.example.auctionapp.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/country")
public class CountryController {

    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService categoryService) {
        this.countryService = categoryService;
    }

    @GetMapping()
    public ResponseEntity<List<CountryDto>> getAll() {

        return new ResponseEntity<>(countryService.getAll(), HttpStatus.OK);
    }
}
