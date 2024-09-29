package com.editor.auth.controller;

import com.editor.auth.config.ApiKeysConfig;
import com.editor.auth.model.Permission;
import com.editor.auth.repository.UserRepo;
import com.editor.auth.service.PermissionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/permission")
@CrossOrigin
public class PersmissionController {
    @Autowired
    private ApiKeysConfig apiKeysConfig;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/add-user-to-document/{docId}")
    public ResponseEntity<?>  addUserToDocument(@PathVariable String docId,@RequestBody Map<String,String> permissionRequest, HttpServletRequest request) {
        System.out.println(docId);
        System.out.println(permissionRequest);
        String apiKey = request.getHeader("api-key");
        if (!apiKeysConfig.getKeys().containsValue(apiKey)) {
            System.out.println(apiKey);
            System.out.println(apiKeysConfig.getKeys());
            System.out.println("Api key match failed");
            return new ResponseEntity<>("Invalid API Key", HttpStatus.UNAUTHORIZED);
        }
        System.out.println("Api key match success");
        permissionService.addPermission(docId, permissionRequest);
        return new ResponseEntity<>("Permission added for valid Users successfully", HttpStatus.CREATED);
    }

    @PostMapping("/remove-user-from-document")
    public ResponseEntity<?> removeUserFromDocument(@RequestBody Map<String, String> requestBody, HttpServletRequest request) {

        String apiKey = request.getHeader("api-key");
        if (!apiKeysConfig.getKeys().containsValue(apiKey)) {
            return new ResponseEntity<>("Invalid API Key", HttpStatus.UNAUTHORIZED);
        }

        String username = requestBody.get("username");
        UUID docId = UUID.fromString(requestBody.get("docId"));

        try{
            permissionService.removePermission(username, docId);
            return new ResponseEntity<>("Permission removed successfully", HttpStatus.UNAUTHORIZED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
    }

    @GetMapping("/get-allowed-users")
    public ResponseEntity<?>  getAllowedUsers(@RequestBody UUID docId, HttpServletRequest request) {
        String apiKey = request.getHeader("api-key");
        if (!apiKeysConfig.getKeys().containsValue(apiKey)) {
            return new ResponseEntity<>("Invalid API Key", HttpStatus.UNAUTHORIZED);
        }
        Iterable<Permission> permissions= permissionService.getPermissions(docId);
        return (ResponseEntity<?>) (permissions);
    }

    @GetMapping("/shared-documents")
    public ResponseEntity<?>  getSharedDocument(HttpServletRequest request) {
        String userId = request.getHeader("x-user-id");
        String apiKey = request.getHeader("api-key");
        if (!apiKeysConfig.getKeys().containsValue(apiKey)) {
            return new ResponseEntity<>("Invalid API Key", HttpStatus.UNAUTHORIZED);
        }

        List<UUID> docIds= permissionService.getSharedDocuments(UUID.fromString(userId));
        return new ResponseEntity<>(docIds, HttpStatus.OK);
    }

    @PostMapping("/can-write")
    public ResponseEntity<?>  canWrite(@RequestBody Map<String,String> requestBody, HttpServletRequest request) {
        String apiKey = request.getHeader("api-key");
        String userId = requestBody.get("userId");
        if (!apiKeysConfig.getKeys().containsValue(apiKey)) {
            System.out.println("Key problem");
            return new ResponseEntity<>("Invalid API Key", HttpStatus.UNAUTHORIZED);
        }
        System.out.println("In can write of Auth");
        UUID docId = UUID.fromString(requestBody.get("docId"));
        boolean canWrite = permissionService.canWrite(userId, docId);
        System.out.println(canWrite);
        return new ResponseEntity<>(canWrite, HttpStatus.OK);
    }

    @PostMapping("/can-read")
    public ResponseEntity<?> canRead(@RequestBody Map<String,String> requestBody, HttpServletRequest request) {
        String apiKey = request.getHeader("api-key");
        String userId = requestBody.get("userId");
        if (!apiKeysConfig.getKeys().containsValue(apiKey)) {
            return new ResponseEntity<>("Invalid API Key", HttpStatus.UNAUTHORIZED);
        }
        UUID docId = UUID.fromString(requestBody.get("docId"));
        boolean canWrite = permissionService.canRead(userId, docId);
        return new ResponseEntity<>(canWrite, HttpStatus.OK);
    }

    @GetMapping("/test")
    public String test(){
        return "Permission under Auth Service";
    }
}
