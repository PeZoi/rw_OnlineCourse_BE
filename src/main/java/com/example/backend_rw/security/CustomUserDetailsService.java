package com.example.backend_rw.security;

import com.example.backend_rw.entity.Status;
import com.example.backend_rw.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component("userDetailsService") // cách làm nâng cao ghi đè
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Tìm user qua email
        com.example.backend_rw.entity.User user = userService.getUserByEmail(email);

        // Nếu không tim thấy user
        if (user == null || (user.getStatus() != null && user.getStatus().equals(Status.DELETED))) {
            throw new UsernameNotFoundException("Email hoặc mật khẩu không chính xác!");
        }

        // Khi tìm được rồi thì lấy ra role
        Set<GrantedAuthority> authorities = Set.of(new SimpleGrantedAuthority(user.getRole().getName()));

        // Kiểm tra xem tài khoản có bị cấm không
        boolean isBlocked = user.getStatus() != null && user.getStatus().equals(Status.BLOCKED);

        // Trả về 1 UserDetail mặc định của spring security
        return new User(user.getEmail(), user.getPassword(), user.isEnabled(), true, true,
                !isBlocked,
                authorities);
    }
}
