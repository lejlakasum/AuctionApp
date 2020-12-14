package com.example.auctionapp.Service;

import com.example.auctionapp.model.Category;
import com.example.auctionapp.model.LoginRequest;
import com.example.auctionapp.security.CustomUserDetails;
import com.example.auctionapp.security.JwtUtil;
import com.example.auctionapp.security.RepositoryAwareUserDetailsService;
import com.example.auctionapp.service.AuthenticationService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class AuthenticationServiceTest {
    @Value("${secret-key}")
    private String SECRET_KEY;

    private AuthenticationService authenticationService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private RepositoryAwareUserDetailsService userDetailsService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        authenticationService = new AuthenticationService(authenticationManager, userDetailsService, SECRET_KEY);
    }

    @Test
    public void loginTest() throws Exception {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken("username",
                "password");
        CustomUserDetails userDetails = new CustomUserDetails(
                "username",
                "password",
                Arrays.asList(new SimpleGrantedAuthority("authority")),
                1000L);

        LoginRequest request = new LoginRequest("username", "password");

        Mockito.when(authenticationManager.authenticate(authentication))
                .thenReturn(authentication);

        Mockito.when(userDetailsService.loadUserByUsername("username"))
                .thenReturn(userDetails);

        assertEquals(JwtUtil.generateToken(userDetails, SECRET_KEY, false), authenticationService.login(request).getToken());

    }
}
