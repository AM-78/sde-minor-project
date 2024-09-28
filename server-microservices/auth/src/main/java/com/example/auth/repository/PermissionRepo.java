package com.example.auth.repository;

import com.example.auth.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PermissionRepo extends JpaRepository<Permission,Integer> {
    List<Permission> findByUserId(UUID userId);
    List<Permission> findByDocId(UUID docId);
    Optional<Permission> findByUserIdAndDocId(UUID userId, UUID docId);
    void deleteByUserIdAndDocId(UUID userId, UUID docId);


}
