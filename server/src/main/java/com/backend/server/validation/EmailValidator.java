package com.backend.server.validation;

import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmailValidator implements Predicate<String> {

    @Override
    public boolean test(String t) {
        // TODO Auto-generated method stub
        return true;
    }}

