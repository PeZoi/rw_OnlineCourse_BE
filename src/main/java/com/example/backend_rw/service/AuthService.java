package com.example.backend_rw.service;

import com.example.backend_rw.entity.dto.CheckValidateCustomerRequest;
import com.example.backend_rw.entity.dto.auth.JWTAuthResponse;
import com.example.backend_rw.entity.dto.auth.LoginDTO;
import com.example.backend_rw.entity.dto.user.UserRequest;
import com.example.backend_rw.entity.dto.user.UserResponse;

import java.util.Map;

public interface AuthService {
    UserResponse register(UserRequest userRequest);

    String verify(String verification, String email);

    JWTAuthResponse login(LoginDTO loginDto);

    void requestPassword(String email);

    UserResponse findByResetPasswordToken(String token);

    void updatePassword(String token, String password);

    Map<String, String> checkInfoOfCustomer(CheckValidateCustomerRequest request);

    JWTAuthResponse refreshToken(String refreshToken);

    void logout();
}
