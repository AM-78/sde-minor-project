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
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PermissionService {

    @Autowired
    private PermissionRepo permissionRepo;

    @Autowired
    private UserRepo userRepo;

    public void addPermission(String docId, Map<String,String> permissionRequests) {
        permissionRequests.forEach((username, canWrite) -> {
            User user = userRepo.findByUsername(username);
            if (user != null) {
                Permission permission = permissionRepo.findByUserIdAndDocId(user.getId(), UUID.fromString(docId))
                        .orElse(new Permission(user.getId(), UUID.fromString(docId), "1".equals(canWrite) ? 1 : 0));
                permission.setCanWrite("1".equals(canWrite) ? 1 : 0);
                permissionRepo.save(permission);
            }
        });
    }

    public void removePermission(String username, UUID docId) throws Exception {
        User user = userRepo.findByUsername(username);
        if(user == null) {
            throw new Exception("User not found");
        }
        permissionRepo.deleteByUserIdAndDocId(user.getId(), docId);
    }

    public Iterable<Permission> getPermissions(UUID docId) {
        return permissionRepo.findByDocId(docId);
    }

    public List<UUID> getSharedDocuments(UUID uuid) {

        List<Permission> permissions =  permissionRepo.findByUserId(uuid);
        return permissions.stream()
                .map(Permission::getDocId)
                .collect(Collectors.toList());
    }

    public boolean canWrite(String userId, UUID docId) {
        Permission permission = permissionRepo.findByUserIdAndDocId(UUID.fromString(userId), docId).orElse(null);
        return permission != null && permission.getCanWrite() == 1;
    }

    public boolean canRead(String userId, UUID docId) {
        Permission permission = permissionRepo.findByUserIdAndDocId(UUID.fromString(userId), docId).orElse(null);
        return permission != null;
    }
}
