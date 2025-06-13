package com.pokeLab.Api.pokeApi.authentication.domain.repository;

import com.pokeLab.Api.pokeApi.authentication.domain.entity.LoginBody;

public interface AuthRepository {

    Integer checkExistenceByUsername(String username) throws Exception;

    String verifyPassword(LoginBody loginBody) throws Exception;

    Integer checkAccountStatus(LoginBody loginBody) throws Exception;

} 
    

