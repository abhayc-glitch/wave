package com.backend.server.repository;

import java.util.Optional;

import com.backend.server.models.ConfirmationToken;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {
    Optional<ConfirmationToken> findByToken(String token);
}
