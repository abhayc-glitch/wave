package com.backend.server.models;

import java.util.Collection;
import java.util.Collections;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
// Automatically generated equals and hashcode values for key-value pairs to Authenticate
@EqualsAndHashCode
// Automatically generates a constructor with a parameter for each field in your class.
@NoArgsConstructor
// Class is an entity and is mapped to a database table
@Entity
@Table(name = "users")
public class User implements UserDetails{
    @Id
    // Generates a primary key to be created when mapping the user entity.
    @SequenceGenerator (
        name = "user_sequence",
        sequenceName = "user_sequence",
        allocationSize = 1)
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "user_sequence"
    )
    private Long id;
    private String username;
    private String name;
    private String password;
    private String email;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    private Boolean locked;
    private Boolean enabled;


    public User(String name,
                String username,
                String password,
                String email,
                UserRole userRole,
                Boolean locked,
                Boolean enabled) {
                    this.name = name;
                    this.username = username;
                    this.password = password;
                    this.email = email;
                    this.userRole = userRole;
                    this.locked = locked;
                    this.enabled = enabled;
    }
    // Returns an array of authorities granted to the user
    // Authorities like accessing certain APi's for registration
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
    
}
