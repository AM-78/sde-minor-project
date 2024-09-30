package com.editor.document.service;

import com.editor.document.model.Document;
import com.editor.document.repository.DocumentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DocumentService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DocumentRepo documentRepo;

    @Autowired
    private DiscoveryClient d;

    public URI getServiceInstanceIp(String serviceId) {
        List<ServiceInstance> instances = d.getInstances(serviceId);

        if (instances != null && !instances.isEmpty()) {
            ServiceInstance instance = instances.get(0); // Get the first instance
            return instance.getUri(); // Returns the IP address of the service
        } else {
            throw new RuntimeException("No instances found for service: " + serviceId);
        }
    }

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

    public boolean isOwner(UUID docId, UUID ownerId) {
        Optional<Document> optionalDocument = documentRepo.findById(docId);
        if (optionalDocument.isPresent()) {
            Document document = optionalDocument.get();
            return document.getOwnerId().toString().equals(ownerId.toString());
        }
        return false;
    }


    public String addUsers(UUID docId, Map<String,String> bodyData) throws Exception {
        String url = getServiceInstanceIp("gateway").toString()+"/auth/permission/add-user-to-document/"+docId;
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
        String url = getServiceInstanceIp("gateway").toString()+"/auth/permission/remove-user-to-document/";
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
        String url = getServiceInstanceIp("gateway").toString()+"/auth/permission/shared-documents";
        HttpHeaders headers = new HttpHeaders();
        headers.set("api-key", "your-document-service-key");
        headers.set("x-user-id", userId);
        HttpEntity<Map<String,String>> entity = new HttpEntity<>(headers);
        ResponseEntity<List> response = restTemplate.exchange(url, HttpMethod.GET, entity, List.class);
        if(response.getStatusCode().is2xxSuccessful()){
            System.out.println("Success from auth............");
            return (List<Document>) response.getBody().stream()
                    .map((docId) -> documentRepo.findById(UUID.fromString(docId.toString())).get())
                    .sorted(Comparator.comparing(Document::getCreationTime).reversed())
                    .collect(Collectors.toList());
        }
        throw new Exception("Failed to add users");
    }

    public List<Document> getMyDocs(UUID ownerId) {
        return documentRepo.findByOwnerIdOrderByCreationTimeDesc(ownerId);
    }
}
