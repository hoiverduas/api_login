package com.fractalia.api_login.service.impl;

import com.fractalia.api_login.dto.AuthenticatedUserResponse;
import com.fractalia.api_login.dto.LoginRequest;
import com.fractalia.api_login.dto.LoginResponse;
import com.fractalia.api_login.feingClients.DummyJsonClient;
import com.fractalia.api_login.model.LoginLog;
import com.fractalia.api_login.repository.LoginLogRepository;
import com.fractalia.api_login.service.IAuthService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class AuthService implements IAuthService {

    private final DummyJsonClient dummyJsonClient;
    private final LoginLogRepository loginLogRepository;

    public static final Integer DEFAULT_EXPIRES_IN_MINS = 30;

    public AuthService(DummyJsonClient dummyJsonClient, LoginLogRepository loginLogRepository) {
        this.dummyJsonClient = dummyJsonClient;
        this.loginLogRepository = loginLogRepository;
    }

    public LoginResponse login(LoginRequest request) {

        request.setExpiresInMins(DEFAULT_EXPIRES_IN_MINS);

        LoginResponse response = dummyJsonClient.login(request);

        LoginLog log = new LoginLog();
        log.setUsername(response.getUsername());
        log.setAccessToken(response.getAccessToken());
        log.setRefreshToken(response.getRefreshToken());
        log.setLoginTime(LocalDateTime.now());

        loginLogRepository.save(log);

        return response;
    }

    public AuthenticatedUserResponse getAuthenticatedUser(String accessToken) {
        String bearerToken = "Bearer " + accessToken;
        return dummyJsonClient.getAuthenticatedUser(bearerToken);
    }



    public Map<String, Object> getAllUsers() {
        return dummyJsonClient.getAllUsers();
    }

}
