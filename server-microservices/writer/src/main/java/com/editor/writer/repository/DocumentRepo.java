package com.editor.writer.repository;


import com.editor.writer.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DocumentRepo extends JpaRepository<Document,Integer> {
    Optional<Document> findById(UUID docId);
    List<Document> findByOwnerId(UUID ownerId);
}
