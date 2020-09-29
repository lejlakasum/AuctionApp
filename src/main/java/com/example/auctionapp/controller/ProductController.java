package com.example.auctionapp.controller;

import com.example.auctionapp.dto.ProductDto;
import com.example.auctionapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController implements IBaseController<ProductDto> {

    @Autowired
    private ProductService productService;

    @GetMapping()
    public ResponseEntity<List<ProductDto>> getAll() {
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }


    public ResponseEntity<ProductDto> getById(Long id) {
        return null;
    }


    public ResponseEntity<ProductDto> add(ProductDto resource) {
        return null;
    }


    public ResponseEntity<ProductDto> update(ProductDto resource) {
        return null;
    }


    public ResponseEntity<Void> deleteById(Long id) {
        return null;
    }
}
