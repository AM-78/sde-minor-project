package com.example.auth.controller;


import com.example.auth.exception.UsernameAlreadyExistsException;
import com.example.auth.service.UserService;
import com.example.auth.model.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService service;


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            User createdUser = service.register(user);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (UsernameAlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody User user){
        return service.verifyUser(user);
    }

    @GetMapping("/get-user")
    public User getUser(HttpServletRequest request){
        String username = (String) request.getAttribute("username");
        return service.getUser(username);
    }

    @GetMapping("/test")
    public String test(){
        return "Test";
    }
}
