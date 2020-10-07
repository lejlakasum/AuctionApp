package com.example.auctionapp.controller;

import com.example.auctionapp.dto.ImageDto;
import com.example.auctionapp.dto.SubcategoryDto;
import com.example.auctionapp.service.ImageService;
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
@RequestMapping("/image")
public class ImageController implements IBaseController<ImageDto> {

    @Autowired
    private ImageService imageService;

    @GetMapping()
    public ResponseEntity<List<ImageDto>> getAll() {

        return new ResponseEntity<>(imageService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImageDto> getById(@PathVariable Long id) {

        return new ResponseEntity<>(imageService.getById(id), HttpStatus.OK);
    }

    @PostMapping()
    @Valid
    public ResponseEntity<ImageDto> add(@Valid @RequestBody ImageDto resource) {

        return new ResponseEntity<>(imageService.add(resource), HttpStatus.CREATED);
    }

    @PutMapping()
    @Valid
    public ResponseEntity<ImageDto> update(@Valid @RequestBody ImageDto resource) {

        return new ResponseEntity<>(imageService.update(resource), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {

        imageService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
