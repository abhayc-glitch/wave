package com.backend.server.service;

import com.backend.server.dto.RegistrationRequest;
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


    
}
