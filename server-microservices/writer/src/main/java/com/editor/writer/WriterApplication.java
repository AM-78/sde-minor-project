package com.editor.writer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class WriterApplication {

	@RequestMapping("/")
	public String home() {
		return "Writer service";
	}

	public static void main(String[] args) {
		SpringApplication.run(WriterApplication.class, args);
	}

}
