package com.example.collabeditor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("")
@RestController
public class RestApiTest {

    @GetMapping("/get")
    public String getit() {
        System.out.println("Get request");
        return "hello";
    }
}
