package com.example.auctionapp.controller;

import com.example.auctionapp.model.LoginRequest;
import com.example.auctionapp.model.LoginResponse;
import com.example.auctionapp.model.User;
import com.example.auctionapp.repository.BaseRepository;
import com.example.auctionapp.security.JwtUtil;
import com.example.auctionapp.security.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    private BaseRepository<User> repository;

    @Autowired
    public void setRepository(BaseRepository<User> repositoryToSet) {
        repository = repositoryToSet;
        repository.setResourceClass(User.class);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());

        final String token = jwtUtil.generateToken(userDetails);

        return new LoginResponse(token);
    }
}
