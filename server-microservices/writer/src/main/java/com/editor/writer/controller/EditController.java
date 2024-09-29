package com.editor.writer.controller;


import com.editor.writer.services.DocumentService;
import com.editor.writer.services.EditService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
public class EditController {

    @Autowired
    private DocumentService documentService;

    @Autowired
    private EditService editService;

    @PostMapping("/write/{docId}")
    public ResponseEntity write(@PathVariable String docId, @RequestBody List<String> requestBody, HttpServletRequest request) {
        System.out.println("In write");
        UUID userId = UUID.fromString(request.getHeader("x-user-id"));
        ObjectMapper objectMapper = new ObjectMapper();
        String content = null;
        try {
            content = objectMapper.writeValueAsString(requestBody);
        } catch (JsonProcessingException e) {
            return ResponseEntity.badRequest().body("Invalid List of String");
        }
        System.out.println(userId+content);
        try{
            if(editService.canWrite(UUID.fromString(docId), userId)){
                System.out.println("it can write.");
                return ResponseEntity.ok().body(editService.write(UUID.fromString(docId), userId, content));
            }
            else{
                return ResponseEntity.badRequest().body("You do not have permission to write to this document");
            }
        } catch (Exception e){
            System.out.println("in exception");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/read")
    public ResponseEntity read(@RequestParam String docId, @RequestParam int version, HttpServletRequest request) {
        System.out.println("In read");
        UUID userId = UUID.fromString(request.getHeader("x-user-id"));
        System.out.println(userId);
        try{
            if(editService.canRead(UUID.fromString(docId), userId)){
                System.out.println("it can read.");
                return ResponseEntity.ok().body(editService.read(UUID.fromString(docId),version));
            }
            else{
                return ResponseEntity.badRequest().body("You do not have permission to read to this document");
            }
        } catch (Exception e){
            System.out.println("in exception");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/test")
    public void test() {
        List<String> originalList = List.of("hello", "world");

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Convert list to JSON string
            String jsonString = objectMapper.writeValueAsString(originalList);
            System.out.println("JSON String: " + jsonString);  // Output: ["hello","world"]

            // Convert JSON string back to list
            List<String> list = objectMapper.readValue(jsonString, new TypeReference<List<String>>() {});
            System.out.println("List: " + list);  // Output: [hello, world]
        } catch (Exception e) {
            e.printStackTrace();
        }

//        return "Write service running";
    }

}
