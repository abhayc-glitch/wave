package com.backend.server.service;

import com.backend.server.repository.UserRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService{

    private final static String USER_NOT_FOUND_msg = "User with email %s is not found";
    // Is the final immutable state always necessary
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
            .orElseThrow(() -> 
                new UsernameNotFoundException((String.format(USER_NOT_FOUND_msg, email))));
    }
    
}
