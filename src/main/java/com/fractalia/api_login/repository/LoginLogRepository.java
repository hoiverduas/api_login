package com.fractalia.api_login.repository;

import com.fractalia.api_login.model.LoginLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginLogRepository extends JpaRepository<LoginLog, Long> {

}
