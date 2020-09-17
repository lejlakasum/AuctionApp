package com.example.auctionapp.controller;

import com.example.auctionapp.dto.RoleDto;
import com.example.auctionapp.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<RoleDto> getAll() {
        return roleService.getAll();
    }

    @GetMapping("/{id}")
    public RoleDto getById(@PathVariable Long id) {
        return roleService.getById(id);
    }

    @PostMapping()
    public RoleDto add(@RequestBody RoleDto resource) {
        return roleService.add(resource);
    }

    @PutMapping()
    public RoleDto update(@RequestBody RoleDto resource) {
        return roleService.update(resource);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        roleService.deleteById(id);
    }
}
