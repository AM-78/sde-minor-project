package com.editor.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class AuthApplication {

	@RequestMapping("/")
	public String home() {
		return "Auth service";
	}
	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}

}
