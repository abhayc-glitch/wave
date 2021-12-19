package com.backend.server.controller;

import com.backend.server.dto.RegistrationRequest;
import com.backend.server.service.RegistrationService;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// Used to create URL mapping
@RequestMapping(path = "api/v1/registration")
public class RegistrationController {

    private RegistrationService registrationService;


    public String register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }

}

