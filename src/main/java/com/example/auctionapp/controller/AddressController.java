package com.example.auctionapp.controller;

import com.example.auctionapp.dto.UserDtos.AddressDto;
import com.example.auctionapp.service.AddressService;
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
@RequestMapping("/user/address")
public class AddressController implements IBaseController<AddressDto> {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping()
    public ResponseEntity<List<AddressDto>> getAll() {

        return new ResponseEntity<>(addressService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDto> getById(@PathVariable Long id) {

        return new ResponseEntity<>(addressService.getById(id), HttpStatus.OK);
    }

    @PostMapping()
    @Valid
    public ResponseEntity<AddressDto> add(@Valid @RequestBody AddressDto resource) {

        return new ResponseEntity<>(addressService.add(resource), HttpStatus.CREATED);
    }

    @PutMapping()
    @Valid
    public ResponseEntity<AddressDto>  update(@Valid @RequestBody AddressDto resource) {

        return new ResponseEntity<>(addressService.update(resource), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {

        addressService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
