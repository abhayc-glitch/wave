package com.backend.server.repository;

import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import com.backend.server.models.User;

import org.springframework.stereotype.Repository;

@Repository
// Makes it so that all the methods are wrapped into one transaction
// ReadOnly makes it so that you can prevent writing errors when querying the DB
@Transactional(readOnly = true)
public interface UserRepository {

    Optional<User> findByEmail(String email);
}
