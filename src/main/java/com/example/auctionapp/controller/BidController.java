package com.example.auctionapp.controller;

import com.example.auctionapp.dto.BidDto;
import com.example.auctionapp.dto.RoleDto;
import com.example.auctionapp.service.BidService;
import com.example.auctionapp.service.RoleService;
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
@RequestMapping("/bid")
public class BidController {

    private final BidService bidService;

    @Autowired
    public BidController(BidService bidService) {
        this.bidService = bidService;
    }

    @GetMapping()
    public ResponseEntity<List<BidDto>> getAll() {

        return new ResponseEntity<>(bidService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BidDto> getById(@PathVariable Long id) {

        return new ResponseEntity<>(bidService.getById(id), HttpStatus.OK);
    }

    @PostMapping()
    @Valid
    public ResponseEntity<BidDto>  add(@Valid @RequestBody BidDto resource) {

        return new ResponseEntity<>(bidService.add(resource), HttpStatus.CREATED);
    }

    @PutMapping()
    @Valid
    public ResponseEntity<BidDto>  update(@Valid @RequestBody BidDto resource) {

        return new ResponseEntity<>(bidService.update(resource), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {

        bidService.deleteById(id);

        return ResponseEntity.noContent().build();
    }


}
