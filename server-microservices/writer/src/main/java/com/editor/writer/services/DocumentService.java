package com.editor.writer.services;

import com.editor.writer.model.Document;
import com.editor.writer.repository.DocumentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepo documentRepo;

    public boolean isOwner(UUID docId, UUID ownerId) {
        Optional<Document> optionalDocument = documentRepo.findById(docId);
        if (optionalDocument.isPresent()) {
            Document document = optionalDocument.get();
            return document.getOwnerId().toString().equals(ownerId.toString());
        }
        return false;
    }

}
