package com.backend.server.repository;

import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import com.backend.server.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
// Makes it so that all the methods are wrapped into one transaction
// ReadOnly makes it so that you can prevent writing errors when querying the DB
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    
    @Transactional
    @Modifying
    @Query("UPDATE User a " +
            "SET a.enabled = TRUE WHERE a.email = ?1")
    int enableUser(String email);
}
