package com.example.backend_rw.controller;

import com.example.backend_rw.entity.User;
import com.example.backend_rw.entity.dto.user.UserResponse;
import com.example.backend_rw.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;
    @GetMapping("/list-all")
    public ResponseEntity<List<UserResponse>> getAll() {
        List<User> users = userRepository.findAll();
        List<UserResponse> userResponses = users.stream().map(user -> {
            UserResponse userResponse = modelMapper.map(user, UserResponse.class);
            userResponse.setRoleName(user.getRole().getName());
            return userResponse;
        }).toList();
        return ResponseEntity.ok(userResponses);
    }
}
