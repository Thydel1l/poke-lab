package com.pokeLab.Api.pokeApi.authentication.interfaces.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pokeLab.Api.pokeApi.authentication.domain.entity.LoginBody;
import com.pokeLab.Api.pokeApi.authentication.domain.entity.LoginRequest;
import com.pokeLab.Api.pokeApi.authentication.domain.usecase.AuthUseCase;
import com.pokeLab.Api.pokeApi.authentication.interfaces.rest.response.LoginResult;
import com.pokeLab.Api.pokeApi.shared.exception.AuthException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthUseCase authUseCase;
    private final AuthMapper authMapper;

    // @Autowired
    public AuthController(AuthUseCase authUseCase, AuthMapper authMapper) {
        this.authUseCase = authUseCase;
        this.authMapper = authMapper;
    }

    @PostMapping("/login")
public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
    try {
        // Validación manual adicional (si quieres más control)
        AuthRequestValidator.validate(loginRequest);

        LoginBody loginBody = authMapper.toLoginBody(loginRequest);
        String token = authUseCase.login(loginBody);
        LoginResult result = new LoginResult(200, token);
        return ResponseEntity.ok(result);

    } catch (AuthException e) {
        return ResponseEntity.status(e.getStatusCode()).body(e.getMessages());
    } catch (Exception e) {
        return ResponseEntity.internalServerError().body("Unexpected error: " + e.getMessage());
    }
}

    @GetMapping("/health")
    public ResponseEntity<?> healthCheck() {
        return ResponseEntity.ok().body("{\"status\": \"UP\"}");
    }
}