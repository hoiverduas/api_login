package com.fractalia.api_login.feingClients;

import com.fractalia.api_login.config.DummyJsonFeignConfig;
import com.fractalia.api_login.dto.AuthenticatedUserResponse;
import com.fractalia.api_login.dto.LoginRequest;
import com.fractalia.api_login.dto.LoginResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

@FeignClient(name = "dummyJsonClient", url = "${dummyjson.api.url}", configuration = DummyJsonFeignConfig.class)
public interface DummyJsonClient {

    @PostMapping(value = "/auth/login", consumes = "application/json")
    LoginResponse login(@RequestBody LoginRequest request);

    @GetMapping("/auth/me")
    AuthenticatedUserResponse getAuthenticatedUser(@RequestHeader("Authorization") String bearerToken);

    @GetMapping("/users")
    Map<String, Object> getAllUsers();
}
