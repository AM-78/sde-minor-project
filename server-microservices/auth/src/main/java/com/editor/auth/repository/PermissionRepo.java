package com.editor.auth.repository;

import com.editor.auth.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PermissionRepo extends JpaRepository<Permission,Integer> {
    List<Permission> findByUserId(UUID userId);
    Iterable<Permission> findByDocId(UUID docId);
    Optional<Permission> findByUserIdAndDocId(UUID userId, UUID docId);
    void deleteByUserIdAndDocId(UUID userId, UUID docId);


}
