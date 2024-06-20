package com.example.backend_rw.entity.dto.auth;

import com.example.backend_rw.entity.dto.user.UserReturnJwt;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"access_token", "token_type", "user"})
public class JWTAuthResponse {

    @JsonProperty("access_token")
    private String accessToken;

    private UserReturnJwt user;
}
