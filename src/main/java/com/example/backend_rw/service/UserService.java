package com.example.backend_rw.service;

import com.example.backend_rw.entity.User;
import com.example.backend_rw.entity.dto.user.UserRequest;
import com.example.backend_rw.entity.dto.user.UserResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    User getUserByEmail(String email);

    List<UserResponse> getAllUsers();

    UserResponse get(Integer userId);

    UserResponse updateInfo(String fullName, MultipartFile img, String email);

    String changePassword(String password, String email);

    UserResponse createUser(UserRequest userRequest, MultipartFile img);

    UserResponse updateUser(UserRequest userRequest, Integer userId, MultipartFile img);

    void updateRefreshTokenUser(String refreshToken, String email);

    String delete(Integer userId);

    String switchStatusBlocked(Integer id);
}
