package com.example.backend_rw.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CheckValidateCustomerRequest {

    private String email;

    private String username;

    private String phoneNumber;
}
