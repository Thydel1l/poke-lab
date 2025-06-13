package com.pokeLab.Api.pokeApi.authentication.infrastructure.persistence.mysql.repository;

import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import com.pokeLab.Api.pokeApi.authentication.domain.entity.LoginBody;

import com.pokeLab.Api.pokeApi.authentication.domain.repository.AuthRepository;
import com.pokeLab.Api.pokeApi.shared.exception.AuthException;

@Repository
public class AuthRepositoryImpl implements AuthRepository {

    private final JdbcTemplate jdbcTemplate;

    public AuthRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Integer checkExistenceByUsername(String username) {
        String sql = "SELECT COUNT(*) FROM users WHERE username = ?";
        try {
            return jdbcTemplate.queryForObject(sql, Integer.class, username);
        } catch (Exception e) {
            throw new AuthException("Error checking existence by username", e);
        }
    }

    @Override
    public String verifyPassword(LoginBody request) {
        String sql = "SELECT id FROM users WHERE username = ? AND password = ?";
        try {
            return jdbcTemplate.queryForObject(sql, String.class, request.getUsername(), request.getPassword());
        } catch (Exception e) {
            throw new AuthException("Error verifying password", e);
        }
    }

    @Override
    public Integer checkAccountStatus(LoginBody request) {
        String sql = "SELECT status FROM users WHERE username = ? AND password = ?";
        try {
            return jdbcTemplate.queryForObject(sql, Integer.class, request.getUsername(), request.getPassword());
        } catch (Exception e) {
            throw new AuthException("Error checking account status", e);
        }
    }
}