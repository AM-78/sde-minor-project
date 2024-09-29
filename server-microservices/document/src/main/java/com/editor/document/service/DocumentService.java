package com.editor.document.service;

import com.editor.document.model.Document;
import com.editor.document.repository.DocumentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DocumentService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DocumentRepo documentRepo;

    public Document createDoc(UUID ownerId) {
        Document document = new Document();
        document.setTitle("Untitled");
        document.setOwnerId(ownerId);
        document.setVersion(0);
        return documentRepo.save(document);
    }

    public String editTitle(UUID docId, String newTitle) throws Exception {
        Optional<Document> optionalDocument = documentRepo.findById(docId);
        if (optionalDocument.isPresent()) {
            Document document = optionalDocument.get();
            document.setTitle(newTitle);
            documentRepo.save(document);
            return "Document title updated to: " + newTitle;
        } else {
            throw new Exception("Document not found");
        }
    }

    public List<Document> getMyDocs(UUID ownerId) {
        return documentRepo.findByOwnerId(ownerId);
    }

    public boolean isOwner(UUID docId, UUID ownerId) {
        Optional<Document> optionalDocument = documentRepo.findById(docId);
        if (optionalDocument.isPresent()) {
            Document document = optionalDocument.get();
            return document.getOwnerId().toString().equals(ownerId.toString());
        }
        return false;
    }

    public List<Document> getAllDocs() {
        return documentRepo.findAll();
    }


    public String addUsers(UUID docId, Map<String,String> bodyData) throws Exception {
        String url = "http://localhost:8080/auth/permission/add-user-to-document/"+docId;
        HttpHeaders headers = new HttpHeaders();
        headers.set("api-key", "your-document-service-key");

        System.out.println(docId);
        System.out.println(bodyData);
        HttpEntity<Map<String,String>> entity = new HttpEntity<>(bodyData, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        if(response.getStatusCode().is2xxSuccessful()){
            System.out.println("Success from auth............");
            return response.getBody();
        }
        throw new Exception("Failed to add users");
    }

    public String removeUser(String docId, String username) throws Exception {
        String url = "http://localhost:8080/auth/permission/remove-user-to-document/";
        HttpHeaders headers = new HttpHeaders();
        headers.set("api-key", "your-document-service-key");

        System.out.println(docId);
        System.out.println(username);
        Map<String, String > bodyData = Map.of();
        bodyData.put("docId", docId);
        bodyData.put("username", username);
        HttpEntity<Map<String,String>> entity = new HttpEntity<>(bodyData, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        if(response.getStatusCode().is2xxSuccessful()){
            System.out.println("Success from auth............");
            return response.getBody();
        }
        throw new Exception("Failed to remove users");
    }

    public List<Document> getSharedDocuments(String userId) throws Exception {
        String url = "http://localhost:8080/auth/permission/shared-documents";
        HttpHeaders headers = new HttpHeaders();
        headers.set("api-key", "your-document-service-key");
        headers.set("x-user-id", userId);
        HttpEntity<Map<String,String>> entity = new HttpEntity<>(headers);
        ResponseEntity<List> response = restTemplate.exchange(url, HttpMethod.GET, entity, List.class);
        if(response.getStatusCode().is2xxSuccessful()){
            System.out.println("Success from auth............");
            return (List<Document>) response.getBody().stream()
                    .map((docId) -> documentRepo.findById(UUID.fromString(docId.toString())).get())
                    .collect(Collectors.toList());
//            return documentRepo.findByOwnerId(UUID.fromString(userId));
        }
        throw new Exception("Failed to add users");
    }
}
