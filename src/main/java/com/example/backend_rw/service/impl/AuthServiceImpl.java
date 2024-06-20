package com.example.backend_rw.service.impl;

import com.example.backend_rw.entity.Role;
import com.example.backend_rw.entity.User;
import com.example.backend_rw.entity.dto.user.UserRequest;
import com.example.backend_rw.entity.dto.user.UserResponse;
import com.example.backend_rw.exception.FieldExistException;
import com.example.backend_rw.repository.RoleRepository;
import com.example.backend_rw.repository.UserRepository;
import com.example.backend_rw.service.AuthService;
import com.example.backend_rw.utils.Constant;
import com.example.backend_rw.utils.EmailUtil;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Transactional
@Service
public class AuthServiceImpl implements AuthService {
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailUtil emailUtil;

    @Value("${online.course.domain.frontend}")
    private String domainFE;

    public AuthServiceImpl(ModelMapper modelMapper, RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, EmailUtil emailUtil) {
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailUtil = emailUtil;
    }


    @Override
    public UserResponse register(UserRequest userRequest) throws FieldExistException {
//      Kiểm tra điều kiện
        if (userRepository.existsByUsername(userRequest.getUsername())) {
            throw new FieldExistException("Tên tài khoản đã tồn tại");
        } else if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new FieldExistException("Email đã tồn tại");
        }

//      Lấy ra role mặc định khi user đăng ký
        Role role = roleRepository.findByName("ROLE_CUSTOMER");
//        Tạo ra random code để verify account
        String randomCode = RandomString.make(64);

        User user = modelMapper.map(userRequest, User.class);
//        Gán mặc định các thuộc cho user
        user.setPhoto("https://res.cloudinary.com/dqnoopa0x/image/upload/v1712482876/ooozzfj7t7p1zokgonni.jpg");
        user.setVerificationCode(randomCode);
        user.setEnabled(false);
        user.setCreatedTime(Instant.now());
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

//        Link để active account
        String verifyURL = domainFE + "/auth/verify?code=" + user.getVerificationCode() + "&email=" + user.getEmail();

        emailUtil.sendEmail(verifyURL, Constant.SUBJECT_REGISTER, Constant.EMAIL_TEMPLATE_REGISTER, user);
        User savedUser = userRepository.save(user);

        UserResponse userResponse = modelMapper.map(savedUser, UserResponse.class);
        userResponse.setRoleName(role.getName());

        return userResponse;
    }
}
