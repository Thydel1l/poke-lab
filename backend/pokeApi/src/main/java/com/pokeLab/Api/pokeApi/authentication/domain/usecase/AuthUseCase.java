package com.pokeLab.Api.pokeApi.authentication.domain.usecase;

import com.pokeLab.Api.pokeApi.authentication.domain.entity.LoginRequest;

public interface  AuthUseCase {
    String login(LoginRequest request) throws Exception;
}
