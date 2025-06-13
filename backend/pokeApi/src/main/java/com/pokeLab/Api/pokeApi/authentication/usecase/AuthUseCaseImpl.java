package com.pokeLab.Api.pokeApi.authentication.usecase;

import org.springframework.stereotype.Service;

import com.pokeLab.Api.pokeApi.authentication.domain.entity.LoginBody;
import com.pokeLab.Api.pokeApi.authentication.domain.repository.AuthRepository;
import com.pokeLab.Api.pokeApi.authentication.domain.usecase.AuthUseCase;
import com.pokeLab.Api.pokeApi.shared.domain.repository.AuthJwtRepository;
import com.pokeLab.Api.pokeApi.shared.exception.AuthException;

@Service
public class AuthUseCaseImpl implements AuthUseCase {

    private final AuthRepository authRepository;
    private final AuthJwtRepository authJwtRepository;

    public AuthUseCaseImpl(AuthRepository authRepository, AuthJwtRepository authJwtRepository) {
        this.authRepository = authRepository;
        this.authJwtRepository = authJwtRepository;
    }

    @Override
    public String login(LoginBody request) throws Exception {
        Integer exists = authRepository.checkExistenceByUsername(request.getUsername());
        if (exists == null || exists != 1) {
            throw new AuthException("Username not found");
        }

        String userId = authRepository.verifyPassword(request);
        if (userId == null) {
            throw new AuthException("Invalid password");
        }

        Integer status = authRepository.checkAccountStatus(request);
        if (status == null || status != 1) {
            throw new AuthException("User account not active");
        }

        return authJwtRepository.generateToken(userId);
    }
}
