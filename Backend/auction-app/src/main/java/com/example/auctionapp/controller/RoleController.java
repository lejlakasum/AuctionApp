package com.example.auctionapp.controller;

import com.example.auctionapp.dto.RoleDto;
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

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController implements IBaseController<RoleDto> {

    @Autowired
    RoleService roleService;

    @GetMapping()
    public ResponseEntity<List<RoleDto>> getAll() {

        return new ResponseEntity<>(roleService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDto> getById(@PathVariable Long id) {

        return new ResponseEntity<>(roleService.getById(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<RoleDto>  add(@RequestBody RoleDto resource) {

        return new ResponseEntity<>(roleService.add(resource), HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<RoleDto>  update(@RequestBody RoleDto resource) {

        return new ResponseEntity<>(roleService.update(resource), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {

        roleService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
