package com.pokeLab.Api.pokeApi.authentication.domain.repository;

import com.pokeLab.Api.pokeApi.authentication.domain.entity.LoginRequest;

public interface AuthRepository {

    Integer checkExistenceByUsername(String username) throws Exception;

    String verifyPassword(LoginRequest request) throws Exception;

    Integer checkAccountStatus(LoginRequest request) throws Exception;

} 
    

