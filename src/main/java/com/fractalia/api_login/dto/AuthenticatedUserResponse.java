package com.fractalia.api_login.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticatedUserResponse {
    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String gender;
    private String image;
}
