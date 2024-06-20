package com.example.backend_rw.service;

import com.example.backend_rw.entity.dto.user.UserRequest;
import com.example.backend_rw.entity.dto.user.UserResponse;
import com.example.backend_rw.exception.FieldExistException;

public interface AuthService {
    UserResponse register(UserRequest userRequest) throws FieldExistException;
}
