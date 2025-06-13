package com.pokeLab.Api.pokeApi.authentication.interfaces.rest;

import org.springframework.stereotype.Component;
import com.pokeLab.Api.pokeApi.authentication.domain.entity.LoginBody;
import com.pokeLab.Api.pokeApi.authentication.domain.entity.LoginRequest;

@Component
public class AuthMapper {
    public LoginBody toLoginBody(LoginRequest request) {
        return new LoginBody(request.getUsername(), request.getPassword());
    }
}