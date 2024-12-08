package com.sash.volleyballApp.controllersREST;

import com.sash.volleyballApp.models.Message;
import com.sash.volleyballApp.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class ChatControllerREST {

    @Autowired
    private ChatService chatService;

    @PostMapping
    public ResponseEntity<Message> sendMessage(
            @RequestParam Long senderId,
            @RequestParam Long receiverId,
            @RequestParam String message) {
        Message chatMessage = chatService.sendMessage(senderId, receiverId, message);
        return ResponseEntity.ok(chatMessage);
    }

    @GetMapping
    public ResponseEntity<List<Message>> getChatHistory(
            @RequestParam Long player1Id,
            @RequestParam Long player2Id) {
        List<Message> chatHistory = chatService.getChatHistory(player1Id, player2Id);
        return ResponseEntity.ok(chatHistory);
    }
}

