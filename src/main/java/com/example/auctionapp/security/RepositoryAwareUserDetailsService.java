package com.example.auctionapp.security;


import com.example.auctionapp.model.User;
import com.example.auctionapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class RepositoryAwareUserDetailsService implements UserDetailsService {

    private final UserRepository repository;

    @Autowired
    public RepositoryAwareUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public CustomUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.findByEmail(email);

        List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(user.getRole().getName()));
        return new CustomUserDetails(user.getEmail(), user.getPassword(), authorities, user.getId());
    }
}
