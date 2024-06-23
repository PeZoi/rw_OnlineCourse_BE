package com.example.backend_rw.service.impl;

import com.example.backend_rw.entity.User;
import com.example.backend_rw.entity.dto.user.UserResponse;
import com.example.backend_rw.repository.UserRepository;
import com.example.backend_rw.service.UserService;
import com.example.backend_rw.utils.UploadFile;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final UploadFile uploadFile;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, UploadFile uploadFile, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.uploadFile = uploadFile;
        this.passwordEncoder = passwordEncoder;
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
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User ID không tồn tại");
        }
        return modelMapper.map(user.get(), UserResponse.class);
    }

    @Override
    public UserResponse updateInfo(String fullName, MultipartFile img, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Email không tồn tại"));

        // Nếu mà có fullName thì tức là người dùng sửa đổi thì cập nhật
        if(fullName != null){
            user.setFullName(fullName);
        }

        if(img != null){
            String urlImage = uploadFile.uploadFileOnCloudinary(img);
            user.setPhoto(urlImage);
        }

        User savedUser = userRepository.save(user);
        UserResponse userResponse = modelMapper.map(savedUser, UserResponse.class);
        userResponse.setRoleName(savedUser.getRole().getName());

        return userResponse;
    }

    @Override
    public String changePassword(String password, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Email không tồn tại"));
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        return "Thay đổi mật khẩu của bạn thành công!";
    }
}
