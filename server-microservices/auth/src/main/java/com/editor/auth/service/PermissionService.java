package com.editor.auth.service;

import com.editor.auth.model.Permission;
import com.editor.auth.model.User;
import com.editor.auth.repository.PermissionRepo;
import com.editor.auth.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class PermissionService {

    @Autowired
    private PermissionRepo permissionRepo;

    @Autowired
    private UserRepo userRepo;

    public void addPermission(List<Map<String,String>> permissionRequests) {
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
    }

    public void removePermission(String username, UUID docId) {
        User user = userRepo.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        permissionRepo.deleteByUserIdAndDocId(user.getId(), docId);
    }

    public Iterable<Permission> getPermissions(UUID docId) {
        return permissionRepo.findByDocId(docId);
    }
}
