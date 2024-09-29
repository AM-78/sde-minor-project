package com.editor.auth.service;


import com.editor.auth.model.User;
import com.editor.auth.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    @Autowired
    private  JWTService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User register(User user) throws Exception {
        if (repo.findByUsername(user.getUsername()) != null) {
            throw new Exception("Username already exists");
        }
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }

    public String verifyUser(User user) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if(authentication.isAuthenticated()){
            user = repo.findByUsername(user.getUsername());
            return jwtService.generateToken(user.getUsername(), String.valueOf(user.getId()));
        }
        return "User Not Authenticated";
    }

    public User getUser(UUID userId) {
        return repo.findById(userId);
    }

    public Iterable<User> getAllUsers() {
        return repo.findAll();
    }

    public Iterable<User> findByUsernameStartingWith(String username) {
        return repo.findByUsernameStartingWith(username);
    }
}
