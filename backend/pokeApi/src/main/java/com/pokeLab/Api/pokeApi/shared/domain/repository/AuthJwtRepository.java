package com.pokeLab.Api.pokeApi.shared.domain.repository;

/**
 * Interfaz para la generación de tokens JWT.
 * Esta interfaz se implementará en la capa de infraestructura.
 */
public interface AuthJwtRepository {
    
    /**
     * Genera un token JWT para un usuario dado.
     * @param userId ID del usuario autenticado.
     * @return Token JWT generado.
     */
    String generateToken(String userId) throws Exception;
}