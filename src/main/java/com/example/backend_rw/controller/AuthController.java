package com.example.backend_rw.controller;

import com.example.backend_rw.entity.dto.CheckValidateCustomerRequest;
import com.example.backend_rw.entity.dto.auth.JWTAuthResponse;
import com.example.backend_rw.entity.dto.auth.LoginDTO;
import com.example.backend_rw.entity.dto.user.UserRequest;
import com.example.backend_rw.entity.dto.user.UserResponse;
import com.example.backend_rw.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<JWTAuthResponse> login(@RequestBody @Valid LoginDTO loginDto) {
        return ResponseEntity.ok().body(authService.login(loginDto));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registration(@RequestBody @Valid UserRequest userRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(userRequest));
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verify(@RequestParam(value = "code") String verification, @RequestParam(value = "email") String email) {

        return ResponseEntity.ok().body(authService.verify(verification, email));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> processRequestFormResetPassword(@RequestParam(value = "email") String email) {
        authService.requestPassword(email);
        return ResponseEntity.ok("Chúng tôi đã gửi một liên kết đặt lại mật khẩu đến địa chỉ email của bạn. Vui lòng kiểm tra!");
    }

    @PostMapping("/handle/reset-password")
    public ResponseEntity<String> showResetForm(@RequestParam(value = "token") String token) {
        UserResponse response = authService.findByResetPasswordToken(token);
        if (response != null) {
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mã không hợp lệ");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> updatePasswordInForgotForm(@RequestParam(value = "token") String token, @RequestParam(value = "password") String newPassword) {
        authService.updatePassword(token, newPassword);
        return ResponseEntity.ok("Bạn đã thay đổi mật khẩu thành công.");
    }

    @PostMapping("/validate")
    public ResponseEntity<?> validateFormSignUp(@RequestBody @Valid CheckValidateCustomerRequest request) {
        Map<String, String> response = authService.checkInfoOfCustomer(request);
        if (response.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(response);
    }

}
