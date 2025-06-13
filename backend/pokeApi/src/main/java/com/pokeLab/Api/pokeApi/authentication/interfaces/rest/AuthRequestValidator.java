package com.pokeLab.Api.pokeApi.authentication.interfaces.rest;

import com.pokeLab.Api.pokeApi.authentication.domain.entity.LoginRequest;
import com.pokeLab.Api.pokeApi.shared.exception.AuthException;

import java.util.ArrayList;
import java.util.List;

public class AuthRequestValidator {

    public static void validate(LoginRequest request) {
        List<String> errors = new ArrayList<>();

        if (request.getUsername() == null || request.getUsername().trim().isEmpty()) {
            errors.add("Username is required");
        }

        if (request.getPassword() == null || request.getPassword().trim().isEmpty()) {
            errors.add("Password is required");
        }

        if (!errors.isEmpty()) {
            throw new AuthException("Validation failed", 400, errors);
        }
    }
}
