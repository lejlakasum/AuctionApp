package com.example.auctionapp.controller;

import com.example.auctionapp.dto.UserDtos.CardInformationDto;
import com.example.auctionapp.service.CardInformarionService;
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
@RequestMapping("/user/card-info")
public class CardInformationController {

    private final CardInformarionService cardInformarionService;

    @Autowired
    public CardInformationController(CardInformarionService cardInformarionService) {
        this.cardInformarionService = cardInformarionService;
    }

    @GetMapping()
    public ResponseEntity<List<CardInformationDto>> getAll() {

        return new ResponseEntity<>(cardInformarionService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardInformationDto> getById(@PathVariable Long id) {

        return new ResponseEntity<>(cardInformarionService.getById(id), HttpStatus.OK);
    }

    @PostMapping()
    @Valid
    public ResponseEntity<CardInformationDto> add(@Valid @RequestBody CardInformationDto resource) {

        return new ResponseEntity<>(cardInformarionService.add(resource), HttpStatus.CREATED);
    }

    @PutMapping()
    @Valid
    public ResponseEntity<CardInformationDto>  update(@Valid @RequestBody CardInformationDto resource) {

        return new ResponseEntity<>(cardInformarionService.update(resource), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {

        cardInformarionService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
