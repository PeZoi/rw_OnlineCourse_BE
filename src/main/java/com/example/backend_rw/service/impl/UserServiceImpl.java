package com.example.backend_rw.service.impl;

import com.example.backend_rw.entity.User;
import com.example.backend_rw.entity.dto.user.UserResponse;
import com.example.backend_rw.exception.CustomException;
import com.example.backend_rw.repository.UserRepository;
import com.example.backend_rw.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Email không tồn tại"));
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> {
            return modelMapper.map(user, UserResponse.class);
        }).toList();
    }

    @Override
    public UserResponse get(Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("User ID không tồn tại");
        }
        return modelMapper.map(user.get(), UserResponse.class);
    }
}
