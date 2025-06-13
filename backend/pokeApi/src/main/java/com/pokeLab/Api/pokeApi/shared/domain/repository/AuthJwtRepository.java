package com.pokeLab.Api.pokeApi.shared.domain.repository;

/**
 * Interfaz para la generación de tokens JWT.
 * Esta interfaz se implementará en la capa de infraestructura.
 */
public interface AuthJwtRepository {
    String generateToken(String username);
    boolean validateToken(String token);
}