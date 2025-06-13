package com.pokeLab.Api.pokeApi.authentication.domain.entity;

public class LoginBody {
    private final String username;
    private final String password;

    public LoginBody(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}