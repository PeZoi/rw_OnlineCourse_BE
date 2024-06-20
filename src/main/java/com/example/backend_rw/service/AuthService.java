package com.example.backend_rw.service;

import com.example.backend_rw.entity.dto.auth.JWTAuthResponse;
import com.example.backend_rw.entity.dto.auth.LoginDTO;
import com.example.backend_rw.entity.dto.user.UserRequest;
import com.example.backend_rw.entity.dto.user.UserResponse;

public interface AuthService {
    UserResponse register(UserRequest userRequest);

    String verify(String verification, String email);

    JWTAuthResponse login(LoginDTO loginDto);
}
