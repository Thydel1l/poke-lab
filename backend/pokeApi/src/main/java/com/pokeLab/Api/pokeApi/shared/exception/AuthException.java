package com.pokeLab.Api.pokeApi.shared.exception;

import com.pokeLab.Api.pokeApi.authentication.domain.error.AuthError;

import java.util.Collections;
import java.util.List;

public class AuthException extends RuntimeException {

    private final AuthError error;
    private final int statusCode;
    private final List<String> messages;

    // Constructor con AuthError
    public AuthException(AuthError error) {
        super(error.getDescription());
        this.error = error;
        this.statusCode = 400;
        this.messages = Collections.singletonList(error.getDescription());
    }

    // Constructor simple con mensaje
    public AuthException(String message) {
        super(message);
        this.error = null;
        this.statusCode = 400;
        this.messages = Collections.singletonList(message);
    }

    // Constructor con mensaje + status + lista
    public AuthException(String message, int statusCode, List<String> messages) {
        super(message);
        this.error = null;
        this.statusCode = statusCode;
        this.messages = messages;
    }

    // Constructor con mensaje + causa
    public AuthException(String message, Throwable cause) {
        super(message, cause);
        this.error = null;
        this.statusCode = 500;
        this.messages = Collections.singletonList(message);
    }

    // Constructor con causa directa
    public AuthException(Throwable cause) {
        super(cause);
        this.error = null;
        this.statusCode = 500;
        this.messages = Collections.singletonList(cause.getMessage());
    }

    // Constructor genérico
    public AuthException() {
        super("Authentication failed");
        this.error = null;
        this.statusCode = 400;
        this.messages = Collections.singletonList("Authentication failed");
    }

    // Getters
    public AuthError getError() {
        return error;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public List<String> getMessages() {
        return messages;
    }
}
