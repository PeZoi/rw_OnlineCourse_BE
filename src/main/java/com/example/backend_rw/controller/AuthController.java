package com.example.backend_rw.controller;

import com.example.backend_rw.entity.dto.CheckValidateCustomerRequest;
import com.example.backend_rw.entity.dto.auth.JWTAuthResponse;
import com.example.backend_rw.entity.dto.auth.LoginDTO;
import com.example.backend_rw.entity.dto.user.UserRequest;
import com.example.backend_rw.entity.dto.user.UserResponse;
import com.example.backend_rw.exception.NotFoundException;
import com.example.backend_rw.repository.UserRepository;
import com.example.backend_rw.service.AuthService;
import com.example.backend_rw.utils.annotation.ApiMessage;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final UserRepository userRepository;

    @Value("${online.course.refresh-token-expiration}")
    private long jwtRefreshTokenExpiration;

    public AuthController(AuthService authService, UserRepository userRepository) {
        this.authService = authService;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    @ApiMessage("Login successful")
    public ResponseEntity<JWTAuthResponse> login(@RequestBody @Valid LoginDTO loginDto) {
        JWTAuthResponse jwtAuthResponse = authService.login(loginDto);
        String refresh_token = userRepository.findByEmail(jwtAuthResponse.getUser().getEmail()).get().getRefreshToken();

        ResponseCookie responseCookie = ResponseCookie.from("refresh_token", refresh_token).httpOnly(true).secure(true).path("/").maxAge(jwtRefreshTokenExpiration).build();

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, responseCookie.toString()).body(jwtAuthResponse);
    }

    @PostMapping("/register")
    @ApiMessage("Register successful")
    public ResponseEntity<UserResponse> registration(@RequestPart(value = "user") @Valid UserRequest userRequest) {
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
    public ResponseEntity<String> updatePasswordInForgotForm(@RequestParam(value = "token") String token, @RequestParam(value = "password") String newPassword) {
        authService.updatePassword(token, newPassword);
        return ResponseEntity.ok("Bạn đã thay đổi mật khẩu thành công.");
    }

    @PostMapping("/validate")
    public ResponseEntity<Map<String, String>> validateFormSignUp(@RequestBody @Valid CheckValidateCustomerRequest request) {
        Map<String, String> response = authService.checkInfoOfCustomer(request);
        if (response.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/refresh-token")
    @ApiMessage("Get user by refresh token")
    public ResponseEntity<JWTAuthResponse> refreshToken(@CookieValue(name = "refresh_token", defaultValue = "nothing") String refreshToken) {
        if (refreshToken.equals("nothing")) {
            throw new NotFoundException("Refresh token không tồn tại");
        }

        JWTAuthResponse jwtAuthResponse = authService.refreshToken(refreshToken);

        String refresh_token = userRepository.findByEmail(jwtAuthResponse.getUser().getEmail()).get().getRefreshToken();

        ResponseCookie responseCookie = ResponseCookie.from("refresh_token", refresh_token).httpOnly(true).secure(true).path("/").maxAge(jwtRefreshTokenExpiration).build();

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, responseCookie.toString()).body(jwtAuthResponse);
    }

}
