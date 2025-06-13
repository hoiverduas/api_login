package com.fractalia.api_login.service.impl;

import com.fractalia.api_login.dto.AuthenticatedUserResponse;
import com.fractalia.api_login.dto.LoginRequest;
import com.fractalia.api_login.dto.LoginResponse;
import com.fractalia.api_login.feingClients.DummyJsonClient;
import com.fractalia.api_login.model.LoginLog;
import com.fractalia.api_login.repository.LoginLogRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private DummyJsonClient dummyJsonClient;

    @Mock
    private LoginLogRepository loginLogRepository;

    @InjectMocks
    private AuthService authService;

    @Test
    void testLoginSuccess() {
        // Arrange
        LoginRequest request = new LoginRequest("emilys", "emilyspass",30);
        LoginResponse expectedResponse = new LoginResponse(
                1L,
                "emilys",
                "emily.johnson@x.dummyjson.com",
                "access-token-123",
                "refresh-token-456"
        );

        Mockito.when(dummyJsonClient.login(request)).thenReturn(expectedResponse);

        // Act
        LoginResponse actualResponse = authService.login(request);

        // Assert
        Assertions.assertEquals(expectedResponse.getUsername(), actualResponse.getUsername());
        Assertions.assertEquals(expectedResponse.getAccessToken(), actualResponse.getAccessToken());

        // Verify save to DB
        Mockito.verify(loginLogRepository, Mockito.times(1)).save(Mockito.any(LoginLog.class));
    }

    @Test
    void testGetAuthenticatedUser() {

        String token = "access-token-123";
        AuthenticatedUserResponse expectedUser = new AuthenticatedUserResponse();
        expectedUser.setId(1L);
        expectedUser.setUsername("emilys");
        expectedUser.setEmail("emily.johnson@x.dummyjson.com");
        expectedUser.setFirstName("Emily");
        expectedUser.setLastName("Johnson");
        expectedUser.setGender("female");
        expectedUser.setImage("https://dummyjson.com/icon/emilys/128");

        Mockito.when(dummyJsonClient.getAuthenticatedUser("Bearer " + token))
                .thenReturn(expectedUser);


        AuthenticatedUserResponse actualUser = authService.getAuthenticatedUser(token);


        Assertions.assertEquals(1L, actualUser.getId());
        Assertions.assertEquals("emilys", actualUser.getUsername());
        Assertions.assertEquals("emily.johnson@x.dummyjson.com", actualUser.getEmail());
        Assertions.assertEquals("Emily", actualUser.getFirstName());
        Assertions.assertEquals("Johnson", actualUser.getLastName());
        Assertions.assertEquals("female", actualUser.getGender());
        Assertions.assertEquals("https://dummyjson.com/icon/emilys/128", actualUser.getImage());
    }

    @Test
    void testGetAllUsers() {

        Map<String, Object> expectedUsers = new HashMap<>();
        expectedUsers.put("total", 1);
        expectedUsers.put("users", List.of(
                Map.of(
                        "id", 1,
                        "username", "emilys",
                        "email", "emily.johnson@x.dummyjson.com"
                )
        ));

        Mockito.when(dummyJsonClient.getAllUsers()).thenReturn(expectedUsers);


        Map<String, Object> actualUsers = authService.getAllUsers();

        Assertions.assertNotNull(actualUsers);
        Assertions.assertEquals(1, actualUsers.get("total"));
        List<Map<String, Object>> users = (List<Map<String, Object>>) actualUsers.get("users");
        Assertions.assertEquals("emilys", users.get(0).get("username"));
    }
}
