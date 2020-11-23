package com.example.auctionapp.controller;

import com.example.auctionapp.dto.UserDtos.CountryDto;
import com.example.auctionapp.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/country")
public class CountryController implements IBaseController<CountryDto> {

    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService categoryService) {
        this.countryService = categoryService;
    }

    @GetMapping()
    public ResponseEntity<List<CountryDto>> getAll() {

        return new ResponseEntity<>(countryService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CountryDto> getById(@PathVariable Long id) {

        return new ResponseEntity<>(countryService.getById(id), HttpStatus.OK);
    }

    @PostMapping()
    @Valid
    public ResponseEntity<CountryDto> add(@Valid @RequestBody CountryDto resource) {

        return new ResponseEntity<>(countryService.add(resource), HttpStatus.CREATED);
    }

    @PutMapping()
    @Valid
    public ResponseEntity<CountryDto>  update(@Valid @RequestBody CountryDto resource) {

        return new ResponseEntity<>(countryService.update(resource), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {

        countryService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
