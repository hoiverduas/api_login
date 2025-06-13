package com.fractalia.api_login.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "loginLog")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @Column(columnDefinition = "TEXT")
    private String accessToken;
    @Column(columnDefinition = "TEXT")
    private String refreshToken;
    private LocalDateTime loginTime;


}
