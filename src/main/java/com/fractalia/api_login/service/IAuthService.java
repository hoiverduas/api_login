package com.fractalia.api_login.service;

import com.fractalia.api_login.dto.AuthenticatedUserResponse;
import com.fractalia.api_login.dto.LoginRequest;
import com.fractalia.api_login.dto.LoginResponse;

import java.util.Map;

public interface IAuthService {

     LoginResponse login(LoginRequest request);
     AuthenticatedUserResponse getAuthenticatedUser(String bearerToken);
     Map<String, Object> getAllUsers();
}
