package com.sash.volleyballApp.controllersREST;

import com.sash.volleyballApp.models.ChatMessage;
import com.sash.volleyballApp.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping
    public ResponseEntity<ChatMessage> sendMessage(
            @RequestParam Long senderId,
            @RequestParam Long receiverId,
            @RequestParam String message) {
        ChatMessage chatMessage = chatService.sendMessage(senderId, receiverId, message);
        return ResponseEntity.ok(chatMessage);
    }

    @GetMapping
    public ResponseEntity<List<ChatMessage>> getChatHistory(
            @RequestParam Long player1Id,
            @RequestParam Long player2Id) {
        List<ChatMessage> chatHistory = chatService.getChatHistory(player1Id, player2Id);
        return ResponseEntity.ok(chatHistory);
    }
}

