package com.example.auctionapp.controller;

import com.example.auctionapp.dto.UserBidDto;
import com.example.auctionapp.dto.UserDtos.UserAccountDto;
import com.example.auctionapp.service.UserService;
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
@RequestMapping("/user")
public class UserController implements IBaseController<UserAccountDto> {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<List<UserAccountDto>> getAll() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserAccountDto> getById(@PathVariable Long id) {

        return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/bids")
    public ResponseEntity<List<UserBidDto>> getBidsByUser(@PathVariable Long id) {

        return new ResponseEntity<>(userService.getBidsByUser(id), HttpStatus.OK);
    }

    @PostMapping()
    @Valid
    public ResponseEntity<UserAccountDto> add(@Valid @RequestBody UserAccountDto resource) {

        return new ResponseEntity<>(userService.add(resource), HttpStatus.CREATED);
    }

    @PutMapping()
    @Valid
    public ResponseEntity<UserAccountDto> update(@Valid @RequestBody UserAccountDto resource) {

        return new ResponseEntity<>(userService.update(resource), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {

        userService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
