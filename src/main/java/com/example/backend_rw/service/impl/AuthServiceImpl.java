package com.example.backend_rw.service.impl;

import com.example.backend_rw.entity.Role;
import com.example.backend_rw.entity.User;
import com.example.backend_rw.entity.dto.auth.JWTAuthResponse;
import com.example.backend_rw.entity.dto.auth.LoginDTO;
import com.example.backend_rw.entity.dto.user.UserRequest;
import com.example.backend_rw.entity.dto.user.UserResponse;
import com.example.backend_rw.entity.dto.user.UserReturnJwt;
import com.example.backend_rw.exception.CustomException;
import com.example.backend_rw.repository.RoleRepository;
import com.example.backend_rw.repository.UserRepository;
import com.example.backend_rw.security.JwtService;
import com.example.backend_rw.service.AuthService;
import com.example.backend_rw.utils.Constant;
import com.example.backend_rw.utils.EmailUtil;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtService jwtService;

    @Value("${online.course.domain.frontend}")
    private String domainFE;

    public AuthServiceImpl(ModelMapper modelMapper, RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, EmailUtil emailUtil, AuthenticationManagerBuilder authenticationManagerBuilder, JwtService jwtService) {
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailUtil = emailUtil;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.jwtService = jwtService;
    }


    @Override
    public UserResponse register(UserRequest userRequest) {
//      Kiểm tra điều kiện
        if (userRepository.existsByUsername(userRequest.getUsername())) {
            throw new CustomException("Tên tài khoản đã tồn tại");
        } else if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new CustomException("Email đã tồn tại");
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

//      Gửi email
        emailUtil.sendEmail(verifyURL, Constant.SUBJECT_REGISTER, Constant.EMAIL_TEMPLATE_REGISTER, user);
        User savedUser = userRepository.save(user);

        UserResponse userResponse = modelMapper.map(savedUser, UserResponse.class);
        userResponse.setRoleName(role.getName());

        return userResponse;
    }

    @Override
    public String verify(String verification, String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new CustomException("Email không tồn tại"));

        if (user.getVerificationCode() == null) {
            throw new CustomException("Tài khoản đã được kích hoạt");
        } else {
            if (user.getVerificationCode().equals(verification)) {
                userRepository.enable(user.getId());
                return "Tài khoản kích hoạt thành công";
            } else {
                throw new CustomException("Sai mã kích hoạt");
            }
        }
    }

    @Override
    public JWTAuthResponse login(LoginDTO loginDto) {
        // Nạp input gồm username/password vào Security
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());
        // Xác thực người dùng => cần viết hàm loadUserByUsername
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        // Tạo token
        String accessToken = jwtService.createToken(authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Lấy ra thông tin cơ bản của user
        User user = userRepository.findByEmail(loginDto.getEmail()).get();
        UserReturnJwt userReturnJwt = modelMapper.map(user, UserReturnJwt.class);
        userReturnJwt.setRoleName(user.getRole().getName());

        return new JWTAuthResponse(accessToken, userReturnJwt);
    }
}