package com.backend.server.service;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import com.backend.server.dto.RegistrationRequest;
import com.backend.server.models.ConfirmationToken;
import com.backend.server.models.User;
import com.backend.server.models.UserRole;
import com.backend.server.validation.EmailValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
@ResponseBody
public class RegistrationService {

    @Autowired
    private UserService userService;
    @Autowired
    private EmailValidator emailValidator;
    @Autowired
    private ConfirmationTokenService confirmationTokenService;
    
    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.
            test(request.getEmail());
        if (!isValidEmail) {
            throw new IllegalStateException("Email is not valid");
        }
        return userService.signUpUser(
            new User(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword(),
                UserRole.USER   
            )
        );
    }
    @Transactional
    public String confirmToken(String token) {
        
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));
        
        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        userService.enableUser(
                confirmationToken.getUser().getEmail());
        return "confirmed";
    }



    
}
