package com.editor.document.controller;

import com.editor.document.model.Document;
import com.editor.document.service.DocumentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.*;

@RestController
@CrossOrigin
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @PostMapping("/create-document")
    public Document createDoc(HttpServletRequest request) {
        String userId = request.getHeader("x-user-id");
        UUID ownerId = UUID.fromString(userId);
        return documentService.createDoc(ownerId);
    }

    @PutMapping("/edit-title")
    public ResponseEntity<String> editTitle(@RequestBody Map<String,String> requestBody, HttpServletRequest request) {
        String userId = request.getHeader("x-user-id");
        UUID docId = UUID.fromString(requestBody.get("docId"));
        String newTitle = requestBody.get("title");
        if(documentService.isOwner(docId, UUID.fromString(userId))){
            try{
                return new ResponseEntity<>(documentService.editTitle(docId,newTitle), HttpStatus.CREATED);
            }
            catch (Exception e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>("You are not the owner of this document", HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/my-documents")
    public List<Document> getMyDocs(HttpServletRequest request) {
        UUID ownerId = UUID.fromString(request.getHeader("x-user-id"));
        return documentService.getMyDocs(ownerId);
    }


    @PostMapping("/add-users/{docId}")
    public ResponseEntity<String> addUsers(@PathVariable String docId,@RequestBody Map<String,String> requestBody, HttpServletRequest request) {
        String userId = request.getHeader("x-user-id");
        UUID documentId = UUID.fromString(docId);
        if(documentService.isOwner(documentId, UUID.fromString(userId))){
            try{
                return new ResponseEntity<>(documentService.addUsers(documentId, requestBody), HttpStatus.CREATED);
            }
            catch (Exception e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>("You are not the owner of this document", HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("remove-user/{docId}")
    public ResponseEntity<String> removeUser(@PathVariable String docId,@RequestBody String username, HttpServletRequest request) {
        String userId = request.getHeader("x-user-id");
        if(documentService.isOwner(UUID.fromString(docId), UUID.fromString(userId))){
            try{
                return new ResponseEntity<>(documentService.removeUser(docId, userId), HttpStatus.CREATED);
            }
            catch (Exception e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>("You are not the owner of this document", HttpStatus.UNAUTHORIZED);
    }


    @GetMapping("/shared-documents")
    public ResponseEntity<?>  getSharedDocument(HttpServletRequest request) {
        String userId = request.getHeader("x-user-id");
        try{
            return new ResponseEntity<>(documentService.getSharedDocuments(userId), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/test")
    public String test(){
        return "Document Service Test";
    }
}