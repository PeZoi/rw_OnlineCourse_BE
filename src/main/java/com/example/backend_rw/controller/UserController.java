package com.example.backend_rw.controller;

import com.example.backend_rw.entity.dto.user.UserRequest;
import com.example.backend_rw.entity.dto.user.UserResponse;
import com.example.backend_rw.service.UserService;
import com.example.backend_rw.utils.annotation.ApiMessage;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list-all")
    @ApiMessage("List all users")
    public ResponseEntity<List<UserResponse>> listAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/get/{id}")
    @ApiMessage("Get user by id")
    public ResponseEntity<UserResponse> getUser(@PathVariable(value = "id") Integer userId) {
        return ResponseEntity.ok(userService.get(userId));
    }

    @PostMapping("/create")
    @ApiMessage("Create user")
    public ResponseEntity<UserResponse> createUser(@RequestPart(value = "user") @Valid UserRequest userRequest, @RequestParam(value = "img", required = false) MultipartFile image) {
        UserResponse savedUser = userService.createUser(userRequest, image);
        URI uri = URI.create("/api/users/" + savedUser.getId());
        return ResponseEntity.created(uri).body(savedUser);
    }

    @PutMapping("/update/{id}")
    @ApiMessage("Update user")
    public ResponseEntity<UserResponse> updateUser(@RequestPart(value = "user") @Valid UserRequest userRequest, @PathVariable(value = "id") Integer userId, @RequestParam(value = "img", required = false) MultipartFile img) {
        return ResponseEntity.ok(userService.updateUser(userRequest, userId, img));
    }

    @PutMapping("/switch-blocked/{id}")
    @ApiMessage("Change status user (block)")
    public ResponseEntity<String> switchStatusBlocked(@PathVariable(value = "id") Integer id) {
        return ResponseEntity.ok(userService.switchStatusBlocked(id));
    }

    @DeleteMapping("/delete/{id}")
    @ApiMessage("Delete user")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Integer userId) {
        return ResponseEntity.ok(userService.delete(userId));
    }

    @PostMapping("/change-info")
    @ApiMessage("Change the user's information")
    public ResponseEntity<UserResponse> updateInfo(@RequestParam(value = "full_name", required = false) String fullName, @RequestParam(value = "img", required = false) MultipartFile img, @RequestParam(value = "email") String email) {
        return ResponseEntity.ok(userService.updateInfo(fullName, img, email));
    }

    @PostMapping("/change-password")
    @ApiMessage("Change password")
    public ResponseEntity<String> changePassword(@RequestParam(value = "password") String newPassword, @RequestParam(value = "email") String email) {
        return ResponseEntity.ok(userService.changePassword(newPassword, email));
    }
}
