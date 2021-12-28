package com.backend.server.service;

import java.time.LocalDateTime;
import java.util.UUID;

import com.backend.server.models.ConfirmationToken;
import com.backend.server.models.User;
import com.backend.server.repository.UserRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService{

    private final static String USER_NOT_FOUND_msg = "User with email %s is not found";
    // Is the final immutable state always necessary
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
            .orElseThrow(() -> 
                new UsernameNotFoundException((String.format(USER_NOT_FOUND_msg, email))));
    }

    public String signUpUser(User user) {
        boolean userExists = userRepository.findByEmail(user.getEmail()).isPresent();

        if (userExists) {
            throw new IllegalStateException("Email is already in use");
        }
        
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);

        userRepository.save(user);

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
            token, 
            LocalDateTime.now(),
            LocalDateTime.now().plusMinutes(15),
            user
        );
        
        confirmationTokenService.saveConfirmationToken(confirmationToken);

        // TODO send the email

        return token;
    }
    
    public int enableUser(String email) {
        return userRepository.enableUser(email);
    }
}
