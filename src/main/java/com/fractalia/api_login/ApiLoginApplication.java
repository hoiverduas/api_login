package com.fractalia.api_login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ApiLoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiLoginApplication.class, args);
	}

}
