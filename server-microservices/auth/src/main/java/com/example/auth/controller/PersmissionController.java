package com.example.auth.controller;

import com.example.auth.config.ApiKeysConfig;
import com.example.auth.model.Permission;
import com.example.auth.model.User;
import com.example.auth.repository.PermissionRepo;
import com.example.auth.repository.UserRepo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController("/permission")
public class PersmissionController {
    @Autowired
    private ApiKeysConfig apiKeysConfig;

    @Autowired
    private PermissionRepo permissionRepo;

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/add-user-to-document")
    public ResponseEntity<?>  addUserToDocument(@RequestBody List<Map<String,String>> permissionRequests, HttpServletRequest request) {
        String apiKey = request.getHeader("api-key");
        if (!apiKeysConfig.getKeys().containsKey(apiKey)) {
            return new ResponseEntity<>("Invalid API Key", HttpStatus.UNAUTHORIZED);
        }

        for (Map<String,String> permissionRequest : permissionRequests) {
            String username = permissionRequest.get("username");
            UUID docId = UUID.fromString(permissionRequest.get("docId"));
            int canWrite = permissionRequest.get("canWrite").equals("1") ? 1 : 0;

            User user = userRepo.findByUsername(username);
            if (user != null) {
                Permission permission = permissionRepo.findByUserIdAndDocId(user.getId(), docId)
                        .orElse(new Permission(user, docId, canWrite));
                permission.setCanWrite(canWrite);
                permissionRepo.save(permission);
            }
        }
        return new ResponseEntity<>("Permission added for successfully", HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/remove-user-from-document")
    public ResponseEntity<?> removeuserFromDocument(@RequestBody Map<String, String> requestBody, HttpServletRequest request) {

        String apiKey = request.getHeader("api-key");
        if (!apiKeysConfig.getKeys().containsKey(apiKey)) {
            return new ResponseEntity<>("Invalid API Key", HttpStatus.UNAUTHORIZED);
        }

        String username = requestBody.get("username");
        UUID docId = UUID.fromString(requestBody.get("docId"));

        User user = userRepo.findByUsername(username);
        if (user != null) {
            permissionRepo.deleteByUserIdAndDocId(user.getId(), docId);
            return new ResponseEntity<>("Permission removed for successfully", HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>("User not found", HttpStatus.UNAUTHORIZED);
    }


    @GetMapping("/get-allowed-users")
    public ResponseEntity<?>  getAllowedUsers(@RequestBody UUID docId, HttpServletRequest request) {
        String apiKey = request.getHeader("api-key");
        if (!apiKeysConfig.getKeys().containsKey(apiKey)) {
            return new ResponseEntity<>("Invalid API Key", HttpStatus.UNAUTHORIZED);
        }
        return (ResponseEntity<?>) (permissionRepo.findByDocId(docId));
    }
}
