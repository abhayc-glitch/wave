package com.backend.server.service;

import com.backend.server.dto.RegistrationRequest;

import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    public String register(RegistrationRequest request) {
        return "works";
    }
    
}
