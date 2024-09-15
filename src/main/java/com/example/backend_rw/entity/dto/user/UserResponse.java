package com.example.backend_rw.entity.dto.user;

import com.example.backend_rw.entity.Role;
import com.example.backend_rw.entity.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    @JsonProperty("user_id")
    private Integer id;

    @JsonProperty("full_name")
    private String fullName;

    private String username;

    private String email;

    @JsonProperty("phone_number")
    private String phoneNumber;

    private String photo;

    @JsonProperty("created_time")
    private Instant createdTime;

    private boolean enabled;

    private Status status;

    private Role role;
}
