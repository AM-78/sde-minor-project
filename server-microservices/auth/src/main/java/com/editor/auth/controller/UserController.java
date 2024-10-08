package com.editor.auth.controller;


import com.editor.auth.service.UserService;
import com.editor.auth.model.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            User createdUser = service.register(user);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody User user){
        return service.verifyUser(user);
    }

    @GetMapping("/get-user")
    public User getUser(HttpServletRequest request){
        UUID userId = UUID.fromString((String) request.getAttribute("user_id"));
        return service.getUser(userId);
    }

    @GetMapping("/get-all-users")
    public Iterable<User> getAllUsers(){
        return service.getAllUsers();
    }

    @GetMapping("/search-by-username")
    public Iterable<User> getUserByUsername(@RequestParam String username){
        return service.findByUsernameStartingWith(username);
    }

    @GetMapping("/test")
    public String test(){
        return "Test";
    }
}
