package com.backend.server.repository;

import java.util.Optional;

import com.backend.server.models.ERole;
import com.backend.server.models.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
