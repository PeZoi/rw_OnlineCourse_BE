package com.example.backend_rw.entity.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private Integer id;

    @NotEmpty(message = "Category name can not be empty")
    @Length(min = 10, max = 45, message = "Category name must have 10-45 characters")
    private String name;

    private String slug;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginDTO {

        @Email(message = "Email is invalid")
        @NotEmpty(message = "Email can not be empty")
        private String email;

        @NotEmpty(message = "Password can not be empty")
        private String password;
    }
}
