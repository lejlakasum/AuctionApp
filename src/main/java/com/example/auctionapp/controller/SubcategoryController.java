package com.example.auctionapp.controller;

import com.example.auctionapp.dto.RoleDto;
import com.example.auctionapp.dto.SubcategoryDto;
import com.example.auctionapp.service.SubcategoryService;
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
@RequestMapping("/subcategory")
public class SubcategoryController implements IBaseController<SubcategoryDto> {

    @Autowired
    SubcategoryService subcategoryService;

    @GetMapping()
    public ResponseEntity<List<SubcategoryDto>> getAll() {

        return new ResponseEntity<>(subcategoryService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubcategoryDto> getById(@PathVariable Long id) {

        return new ResponseEntity<>(subcategoryService.getById(id), HttpStatus.OK);
    }

    @PostMapping()
    @Valid
    public ResponseEntity<SubcategoryDto> add(@Valid @RequestBody SubcategoryDto resource) {

        return new ResponseEntity<>(subcategoryService.add(resource), HttpStatus.CREATED);
    }

    @PutMapping()
    @Valid
    public ResponseEntity<SubcategoryDto> update(@Valid @RequestBody SubcategoryDto resource) {

        return new ResponseEntity<>(subcategoryService.update(resource), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {

        subcategoryService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
