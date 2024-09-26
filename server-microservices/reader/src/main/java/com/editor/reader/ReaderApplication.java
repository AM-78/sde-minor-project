package com.editor.reader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class ReaderApplication {

	@RequestMapping("/")
	public String home() {
		return "Reader service";
	}

	public static void main(String[] args) {
		SpringApplication.run(ReaderApplication.class, args);
	}

}
