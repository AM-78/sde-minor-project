package com.editor.auth.controller;

import com.editor.auth.config.ApiKeysConfig;
import com.editor.auth.service.JWTService;
import com.editor.auth.service.MyUserDetailService;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/jwt")
public class JWTController {

    @Autowired
    private JWTService jwtService;

    @Autowired
    ApplicationContext context;


    @PostMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestBody String token) {
        String username = jwtService.extractUserName(token);
        String userId = jwtService.extractUserId(token);
        UserDetails userDetails = context.getBean(MyUserDetailService.class).loadUserByUsername(username);
        if(jwtService.validateToken(token, userDetails)){
            return new ResponseEntity<>(userId, HttpStatus.OK);
        }
        return new ResponseEntity<>("Token is invalid", HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/test")
    public String test(){
        return "JWT Service Test";
    }
}
