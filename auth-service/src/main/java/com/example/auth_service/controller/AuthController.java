package com.example.auth_service.controller;

import com.example.auth_service.dto.AuthResponseDTO;
import com.example.auth_service.dto.LoginRequestDTO;
import com.example.auth_service.dto.RegisterRequestDTO;
import com.example.auth_service.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        AuthResponseDTO response = authService.login(loginRequestDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@Valid @RequestBody RegisterRequestDTO registerRequestDTO) {
        AuthResponseDTO response = authService.register(registerRequestDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/secured")
    public ResponseEntity<String> securedEndpoint(){

        return ResponseEntity.ok("Başarılı");
    }

}