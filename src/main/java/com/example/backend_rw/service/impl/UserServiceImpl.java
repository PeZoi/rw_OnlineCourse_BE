package com.example.backend_rw.service.impl;

import com.example.backend_rw.entity.Role;
import com.example.backend_rw.entity.Status;
import com.example.backend_rw.entity.User;
import com.example.backend_rw.entity.dto.user.UserRequest;
import com.example.backend_rw.entity.dto.user.UserResponse;
import com.example.backend_rw.exception.NotFoundException;
import com.example.backend_rw.repository.RoleRepository;
import com.example.backend_rw.repository.UserRepository;
import com.example.backend_rw.service.UserService;
import com.example.backend_rw.utils.UploadFile;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private final UploadFile uploadFile;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, ModelMapper modelMapper, UploadFile uploadFile, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
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
        List<User> users = userRepository.findAllUsers();
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
        if (user.get().getStatus().equals(Status.DELETED)) {
            throw new UsernameNotFoundException("User đã bị xoá");
        }
        return modelMapper.map(user.get(), UserResponse.class);
    }

    @Override
    public UserResponse updateInfo(String fullName, MultipartFile img, String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Email không tồn tại"));

        if (user.getStatus().equals(Status.DELETED)) {
            throw new UsernameNotFoundException("User đã bị xoá");
        }

        // Nếu mà có fullName thì tức là người dùng sửa đổi thì cập nhật
        if (fullName != null) {
            user.setFullName(fullName);
        }

        if (img != null) {
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
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Email không tồn tại"));
        if (user.getStatus().equals(Status.DELETED)) {
            throw new UsernameNotFoundException("User đã bị xoá");
        }
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        return "Thay đổi mật khẩu của bạn thành công!";
    }

    @Override
    public UserResponse createUser(UserRequest userRequest, MultipartFile img) {
        // Lấy ra role admin
        Role role = roleRepository.findByName("ROLE_ADMIN");

        User user = checkValid(userRequest, role, img);
        User savedUser = userRepository.save(user);

        return convertToUserResponse(savedUser);
    }

    @Override
    public UserResponse updateUser(UserRequest userRequest, Integer userId, MultipartFile img) {
        // Lấy user từ db
        User userInDB = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User ID không tồn tại"));
        if (userInDB.getStatus().equals(Status.DELETED)) {
            throw new UsernameNotFoundException("User đã bị xoá");
        }
        // Nếu có ảnh đại diện có thay đổi
        if (img != null) {
            if (userInDB.getPhoto() != null) {
                uploadFile.deleteImageInCloudinary(userInDB.getPhoto());
            }
            String url = uploadFile.uploadFileOnCloudinary(img);
            userInDB.setPhoto(url);
        }

        // Nếu mật khẩu có thay đổi
        if (!userRequest.getPassword().equals("Unknown password")) {
            userInDB.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        }

        userInDB.setFullName(userRequest.getFullName());
        userInDB.setEmail(userRequest.getEmail());
        userInDB.setPhoneNumber(userRequest.getPhoneNumber());
        userInDB.setEnabled(userRequest.isEnabled());

        User savedUser = userRepository.save(userInDB);
        return convertToUserResponse(savedUser);
    }

    @Override
    public void updateRefreshTokenUser(String refreshToken, String email) {
        User userInDB =
                userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Email không tồn " + "tại"));
        if (userInDB.getStatus().equals(Status.DELETED)) {
            throw new UsernameNotFoundException("User đã bị xoá");
        }
        userInDB.setRefreshToken(refreshToken);
        userRepository.save(userInDB);
    }

    @Override
    public String delete(Integer userId) {
        // Lấy user trong db
        User userInDB = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User ID không tồn tại"));
        // Xoá ảnh trong cloudinary
//        if (userInDB.getPhoto() != null) {
//            uploadFile.deleteImageInCloudinary(userInDB.getPhoto());
//        }
        userRepository.deleteUser(userInDB.getId());
        return "Xóa user thành công";
    }

    private UserResponse convertToUserResponse(User user) {
        UserResponse userResponse = modelMapper.map(user, UserResponse.class);
        userResponse.setRoleName(user.getRole().getName());
        return userResponse;
    }

    private User checkValid(UserRequest userRequest, Role role, MultipartFile img) {
        User user = modelMapper.map(userRequest, User.class);

        // upload ảnh vô cloudinary (nếu người dùng upload ảnh)
        if (img != null) {
            String url = uploadFile.uploadFileOnCloudinary(img);
            user.setPhoto(url);
        }

        user.setEnabled(true);
        user.setCreatedTime(Instant.now());
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return user;
    }
}
