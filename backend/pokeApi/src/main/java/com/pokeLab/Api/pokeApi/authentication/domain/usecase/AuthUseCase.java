package com.pokeLab.Api.pokeApi.authentication.domain.usecase;

import com.pokeLab.Api.pokeApi.authentication.domain.entity.LoginBody;

public interface AuthUseCase {
    String login(LoginBody loginBody) throws Exception;
}