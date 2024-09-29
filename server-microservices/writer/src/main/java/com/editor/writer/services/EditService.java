package com.editor.writer.services;


import com.editor.writer.model.Document;
import com.editor.writer.model.Edit;
import com.editor.writer.repository.DocumentRepo;
import com.editor.writer.repository.EditRepo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class EditService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DocumentRepo documentRepo;
    @Autowired
    private EditRepo editRepo;

    public boolean canWrite(UUID docID, UUID userid) throws Exception {
        Document document = documentRepo.findById(docID).get();
        if(userid.equals(document.getOwnerId())){
            return true;
        }
        String url = "http://localhost:8080/auth/permission/can-write";
        HttpHeaders headers = new HttpHeaders();
        headers.set("api-key", "your-writer-service-key");

        Map<String, String > bodyData = Map.of("docId", docID.toString(), "userId", userid.toString());
        HttpEntity<Map<String,String>> entity = new HttpEntity<>(bodyData, headers);
        ResponseEntity<Boolean> response = restTemplate.exchange(url, HttpMethod.POST, entity, Boolean.class);
        System.out.println("Response: " + response);
        return Boolean.TRUE.equals(response.getBody());
    }

    @Transactional
    public int write(UUID docId, UUID userId, String content){

        Optional<Document> doc = documentRepo.findById(docId);
        if(doc.isPresent()){
            Document document = doc.get();
            Edit edit = new Edit(docId, userId, content, document.getVersion()+1);
            editRepo.save(edit);
            System.out.println("Edit: "+edit);
            document.setVersion(document.getVersion()+1);
            documentRepo.save(document);
            System.out.println("Document: "+document);
            return document.getVersion()+1;
        }
        throw new RuntimeException("Document not found");
    }


    public boolean canRead(UUID docID, UUID userid) throws Exception {
        Document document = documentRepo.findById(docID).get();
        if(userid.equals(document.getOwnerId())){
            return true;
        }
        String url = "http://localhost:8080/auth/permission/can-read";
        HttpHeaders headers = new HttpHeaders();
        headers.set("api-key", "your-writer-service-key");

        Map<String, String > bodyData = Map.of("docId", docID.toString(), "userId", userid.toString());
        HttpEntity<Map<String,String>> entity = new HttpEntity<>(bodyData, headers);
        ResponseEntity<Boolean> response = restTemplate.exchange(url, HttpMethod.POST, entity, Boolean.class);
        System.out.println("Response: " + response);
        return Boolean.TRUE.equals(response.getBody());
    }

    public List<Edit> read(UUID docId, int version) {
        return editRepo.findByDocIdAndVersionGreaterThan(docId, version);
    }
}
