package com.example.auctionapp.controller;

import com.example.auctionapp.model.LoginRequest;
import com.example.auctionapp.model.LoginResponse;
import com.example.auctionapp.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) throws Exception {

        return new ResponseEntity<>(authenticationService.login(loginRequest), HttpStatus.CREATED);
    }
}
