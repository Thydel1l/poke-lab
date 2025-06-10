package com.pokeLab.Api.pokeApi.authentication.usecase;

import org.springframework.stereotype.Service;

import com.pokeLab.Api.pokeApi.authentication.domain.entity.LoginRequest;
import com.pokeLab.Api.pokeApi.authentication.domain.repository.AuthRepository;
import com.pokeLab.Api.pokeApi.authentication.domain.usecase.AuthUseCase;
import com.pokeLab.Api.pokeApi.shared.domain.repository.AuthJwtRepository;
import com.pokeLab.Api.pokeApi.shared.exception.AuthException;

/**
 * Implementación concreta del caso de uso de autenticación (Login).
 * Pertenece a la capa de "UseCase" en la arquitectura limpia.
 *
 * Su propósito es:
 * - Validar que el usuario exista.
 * - Verificar la contraseña.
 * - Verificar que la cuenta esté activa.
 * - Generar un token JWT si todo es correcto.
 */
@Service
public class AuthUseCaseImpl implements AuthUseCase {

    // Dependencia que accede a la capa de repositorio de usuarios
    private final AuthRepository authRepository;

    // Dependencia que se encarga de generar tokens JWT (abstracción en shared)
    private final AuthJwtRepository authJwtRepository;

    // Inyección de dependencias vía constructor
    public AuthUseCaseImpl(AuthRepository authRepository, AuthJwtRepository authJwtRepository) {
        this.authRepository = authRepository;
        this.authJwtRepository = authJwtRepository;
    }

    /**
     * Ejecuta el proceso completo de login.
     * 
     * @param request DTO con username y password.
     * @return JWT si la autenticación fue exitosa.
     * @throws AuthException si algo falla en el proceso.
     */
    @Override
    public String login(LoginRequest request) throws Exception {
        // Paso 1: Verificar existencia del usuario
        Integer exists = authRepository.checkExistenceByUsername(request.getUsername());
        if (exists == null || exists != 1) {
            throw new AuthException("Username not found");
        }

        // Paso 2: Verificar la contraseña
        String userId = authRepository.verifyPassword(request);
        if (userId == null) {
            throw new AuthException("Invalid password");
        }

        // Paso 3: Verificar que la cuenta esté activa
        Integer status = authRepository.checkAccountStatus(request);
        if (status == null || status != 1) {
            throw new AuthException("User account not active");
        }

        // Paso 4: Generar y retornar el token
        return authJwtRepository.generateToken(userId);
    }
}