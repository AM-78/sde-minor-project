package com.editor.auth.controller;

import com.editor.auth.config.ApiKeysConfig;
import com.editor.auth.model.Permission;
import com.editor.auth.model.User;
import com.editor.auth.repository.PermissionRepo;
import com.editor.auth.repository.UserRepo;
import com.editor.auth.service.PermissionService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController("/permission")
@CrossOrigin
public class PersmissionController {
    @Autowired
    private ApiKeysConfig apiKeysConfig;


    @Autowired
    private PermissionService permissionService;

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/add-user-to-document")
    public ResponseEntity<?>  addUserToDocument(@RequestBody List<Map<String,String>> permissionRequests, HttpServletRequest request) {
        String apiKey = request.getHeader("api-key");
        if (!apiKeysConfig.getKeys().containsKey(apiKey)) {
            return new ResponseEntity<>("Invalid API Key", HttpStatus.UNAUTHORIZED);
        }
        permissionService.addPermission(permissionRequests);
        return new ResponseEntity<>("Permission added for valid Users successfully", HttpStatus.CREATED);
    }

    @PostMapping("/remove-user-from-document")
    public ResponseEntity<?> removeuserFromDocument(@RequestBody Map<String, String> requestBody, HttpServletRequest request) {

        String apiKey = request.getHeader("api-key");
        if (!apiKeysConfig.getKeys().containsKey(apiKey)) {
            return new ResponseEntity<>("Invalid API Key", HttpStatus.UNAUTHORIZED);
        }

        String username = requestBody.get("username");
        UUID docId = UUID.fromString(requestBody.get("docId"));

        try{
            permissionService.removePermission(username, docId);
            return new ResponseEntity<>("Permission removed successfully", HttpStatus.UNAUTHORIZED);
        }
        catch (UsernameNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
    }


    @GetMapping("/get-allowed-users")
    public ResponseEntity<?>  getAllowedUsers(@RequestBody UUID docId, HttpServletRequest request) {
        String apiKey = request.getHeader("api-key");
        if (!apiKeysConfig.getKeys().containsKey(apiKey)) {
            return new ResponseEntity<>("Invalid API Key", HttpStatus.UNAUTHORIZED);
        }
        Iterable<Permission> permissions= permissionService.getPermissions(docId);
        return (ResponseEntity<?>) (permissions);
    }
}
