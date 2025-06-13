package com.pokeLab.Api.pokeApi.authentication.interfaces.rest.response;

public class LoginResult {
    private int statusCode;
    private String token;

    public LoginResult(int statusCode, String token) {
        this.statusCode = statusCode;
        this.token = token;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getToken() {
        return token;
    }
}