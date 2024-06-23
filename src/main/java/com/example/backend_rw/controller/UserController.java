package com.example.backend_rw.controller;

import com.example.backend_rw.entity.dto.user.UserResponse;
import com.example.backend_rw.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list-all")
    public ResponseEntity<List<UserResponse>> listAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable(value = "id") Integer userId){
        return ResponseEntity.ok(userService.get(userId));
    }

    @PostMapping("/change-info")
    public ResponseEntity<UserResponse> updateInfo(@RequestParam(value = "full_name", required = false) String fullName,
                                                           @RequestParam(value = "img", required = false) MultipartFile img,
                                                           @RequestParam(value = "email") String email){
        return ResponseEntity.ok(userService.updateInfo(fullName, img, email));
    }
    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestParam(value = "password") String newPassword,
                                                 @RequestParam(value = "email") String email){
        return ResponseEntity.ok(userService.changePassword(newPassword, email));
    }
}
