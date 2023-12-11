package edu.whu.controller;

import edu.whu.service.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping("/message")
    public ResponseEntity<?> receiveMessage(@RequestBody String message) {
        try {
            chatService.chatWithModelAndSave(message);
            return ResponseEntity.ok("Message processed successfully");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error processing message: " + e.getMessage());
        }
    }
}

