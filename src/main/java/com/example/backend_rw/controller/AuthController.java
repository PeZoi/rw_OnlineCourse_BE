package com.example.backend_rw.controller;

import com.example.backend_rw.entity.dto.user.UserRequest;
import com.example.backend_rw.entity.dto.user.UserResponse;
import com.example.backend_rw.exception.FieldExistException;
import com.example.backend_rw.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registration(@RequestBody @Valid UserRequest userRequest) throws FieldExistException {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(userRequest));
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verify(@RequestParam(value = "code") String verification, @RequestParam(value = "email") String email) {

        return ResponseEntity.ok().body(authService.verify(verification, email));
    }
}
