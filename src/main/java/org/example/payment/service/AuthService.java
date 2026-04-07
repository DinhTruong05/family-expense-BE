package org.example.payment.service;

import org.example.payment.Dto.request.LoginRequest;
import org.example.payment.Dto.request.RegisterRequest;
import org.example.payment.Dto.response.AuthResponse;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}