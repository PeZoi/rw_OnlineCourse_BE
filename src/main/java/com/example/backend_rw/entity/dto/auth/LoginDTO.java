package com.example.backend_rw.entity.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {

    @Email(message = "Email is invalid")
    @NotEmpty(message = "Email can not be empty")
    private String email;

    @NotEmpty(message = "Password can not be empty")
    private String password;
}
