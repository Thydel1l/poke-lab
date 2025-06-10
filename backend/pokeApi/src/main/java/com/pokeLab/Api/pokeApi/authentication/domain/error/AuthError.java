package com.pokeLab.Api.pokeApi.authentication.domain.error;

public class AuthError {
    private final String code;
    private final String description;
    private final String layer;
    private final String function;
    private final int httpStatus;
    
    private AuthError(Builder builder) {
        this.code = builder.code;
        this.description = builder.description;
        this.layer = builder.layer;
        this.function = builder.function;
        this.httpStatus = builder.httpStatus;
    }
    public static class Builder {
        private String code;
        private String description;
        private String layer;
        private String function;
        private int httpStatus;

        public Builder setCode(String code) {
            this.code = code;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setLayer(String layer) {
            this.layer = layer;
            return this;
        }

        public Builder setFunction(String function) {
            this.function = function;
            return this;
        }

        public Builder setHttpStatus(int httpStatus) {
            this.httpStatus = httpStatus;
            return this;
        }

        public AuthError build() {
            return new AuthError(this);
        }
    }
    // Getters (puedes usar lombok para acortar esto si lo deseas)
    public String getCode() { return code; }
    public String getDescription() { return description; }
    public String getLayer() { return layer; }
    public String getFunction() { return function; }
    public int getHttpStatus() { return httpStatus; }

    // Errores comunes como constantes:
    public static final AuthError ERR_NOT_FOUND_USERNAME = new AuthError.Builder()
        .setCode("ERR_NOT_FOUND_USERNAME")
        .setDescription("USERNAME NOT FOUND")
        .setLayer("UseCase")
        .setFunction("Login")
        .setHttpStatus(404)
        .build();

    public static final AuthError ERR_NOT_FOUND_PASSWORD = new AuthError.Builder()
        .setCode("ERR_NOT_FOUND_PASSWORD")
        .setDescription("PASSWORD NOT FOUND")
        .setLayer("UseCase")
        .setFunction("Login")
        .setHttpStatus(401)
        .build();

    public static final AuthError ERR_USER_STATUS_NOT_ACTIVE = new AuthError.Builder()
        .setCode("ERR_USER_STATUS_NOT_ACTIVE")
        .setDescription("USER STATUS NOT ACTIVE")
        .setLayer("UseCase")
        .setFunction("Login")
        .setHttpStatus(403)
        .build();
}
