package org.example.payment.Controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.payment.Dto.request.LoginRequest;
import org.example.payment.Dto.request.RegisterRequest;
import org.example.payment.Dto.response.AuthResponse;
import org.example.payment.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}