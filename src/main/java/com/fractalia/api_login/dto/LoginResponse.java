package com.fractalia.api_login.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private Long id;
    private String username;
    private String email;
    private String accessToken;
    private String refreshToken;

}
