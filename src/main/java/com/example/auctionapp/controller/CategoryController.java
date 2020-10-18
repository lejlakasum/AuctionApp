package com.example.auctionapp.controller;

import com.example.auctionapp.dto.CategoryDto;
import com.example.auctionapp.dto.RoleDto;
import com.example.auctionapp.service.CategoryService;
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
@RequestMapping("/category")
public class CategoryController implements IBaseController<CategoryDto> {

    @Autowired
    CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<List<CategoryDto>> getAll() {

        return new ResponseEntity<>(categoryService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getById(@PathVariable Long id) {

        return new ResponseEntity<>(categoryService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/feature")
    public ResponseEntity<List<CategoryDto>> getFeatureCategories() {
        return new ResponseEntity<>(categoryService.findFeatureCategories(), HttpStatus.OK);
    }

    @PostMapping()
    @Valid
    public ResponseEntity<CategoryDto> add(@Valid @RequestBody CategoryDto resource) {

        return new ResponseEntity<>(categoryService.add(resource), HttpStatus.CREATED);
    }

    @PutMapping()
    @Valid
    public ResponseEntity<CategoryDto>  update(@Valid @RequestBody CategoryDto resource) {

        return new ResponseEntity<>(categoryService.update(resource), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {

        categoryService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
