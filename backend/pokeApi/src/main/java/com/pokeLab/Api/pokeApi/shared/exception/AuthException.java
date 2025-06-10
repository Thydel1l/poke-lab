package com.pokeLab.Api.pokeApi.shared.exception;

import com.pokeLab.Api.pokeApi.authentication.domain.error.AuthError;

public class AuthException extends RuntimeException {

    private final AuthError error;

    public AuthException(AuthError error) {
        super(error.getDescription());
        this.error = error;
    }

    public AuthException(String message) {
        super(message);
        this.error = null;
    }

    public AuthException(String message, Throwable cause) {
        super(message, cause);
        this.error = null;
    }

    public AuthException(Throwable cause) {
        super(cause);
        this.error = null;
    }

    public AuthException() {
        super("Authentication failed");
        this.error = null;
    }

    public AuthError getError() {
        return error;
    }
}
