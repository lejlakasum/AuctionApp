package com.example.auctionapp.controller;

import com.example.auctionapp.model.LoginRequest;
import com.example.auctionapp.dto.AuthenticationDto;
import com.example.auctionapp.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationDto> login(@RequestBody LoginRequest loginRequest) throws Exception {

        try {
            return new ResponseEntity<>(authenticationService.login(loginRequest), HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/token/refresh")
    public ResponseEntity<AuthenticationDto> refresh(@RequestBody AuthenticationDto authenticationDto) {
        try {
            return new ResponseEntity<>(authenticationService.refresh(authenticationDto), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
