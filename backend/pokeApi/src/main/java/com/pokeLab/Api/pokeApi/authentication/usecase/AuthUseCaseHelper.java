package com.pokeLab.Api.pokeApi.authentication.usecase;

import com.pokeLab.Api.pokeApi.authentication.domain.entity.LoginRequest;
import com.pokeLab.Api.pokeApi.authentication.domain.error.AuthError;
import com.pokeLab.Api.pokeApi.authentication.domain.repository.AuthRepository;
import com.pokeLab.Api.pokeApi.shared.exception.AuthException;

import org.springframework.stereotype.Component;

/**
 * Clase auxiliar del caso de uso de autenticación.
 * Se encarga de validaciones repetitivas para mantener
 * el código del caso de uso principal (`AuthUseCaseImpl`) más limpio.
 */
@Component
public class AuthUseCaseHelper {

    private final AuthRepository authRepository;

    public AuthUseCaseHelper(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    /**
     * Verifica si el usuario existe.
     * Lanza una excepción personalizada si no existe.
     */
    public void validateUserExistence(String username) throws AuthException, Exception {
        Integer exists = authRepository.checkExistenceByUsername(username);
        if (exists == null || exists != 1) {
            throw new AuthException(AuthError.ERR_NOT_FOUND_USERNAME);
        }
    }

    /**
     * Verifica que la contraseña sea válida.
     * Retorna el userId si es válida; lanza excepción si no lo es.
     */
    public String validatePassword(LoginRequest request) throws AuthException, Exception {
        String userId = authRepository.verifyPassword(request);
        if (userId == null) {
            throw new AuthException(AuthError.ERR_NOT_FOUND_PASSWORD);
        }
        return userId;
    }

    /**
     * Verifica que el estado de la cuenta sea "activa".
     * Lanza excepción si no lo es.
     */
    public void validateUserStatus(LoginRequest request) throws AuthException, Exception {
        Integer status = authRepository.checkAccountStatus(request);
        if (status == null || status != 1) {
            throw new AuthException(AuthError.ERR_USER_STATUS_NOT_ACTIVE);
        }
    }
}
