package com.backend.server.service;

import com.backend.server.models.ConfirmationToken;
import com.backend.server.repository.ConfirmationTokenRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class ConfirmationTokenService {
    
    private ConfirmationTokenRepository confirmationTokenRepository;

    public void saveConfirmationToken(ConfirmationToken token) {
        confirmationTokenRepository.save(token);
    }
}
