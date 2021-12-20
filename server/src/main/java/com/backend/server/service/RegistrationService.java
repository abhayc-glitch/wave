package com.backend.server.service;

import com.backend.server.dto.RegistrationRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
@ResponseBody
public class RegistrationService {
    public String register(RegistrationRequest request) {
        return "works";
    }
    
}
