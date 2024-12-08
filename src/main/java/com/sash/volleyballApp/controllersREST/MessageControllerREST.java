package com.sash.volleyballApp.controllersREST;

import com.sash.volleyballApp.models.Message;
import com.sash.volleyballApp.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageControllerREST {

    @Autowired
    private MessageService messageService;

    @GetMapping("/{user1Id}/{user2Id}")
    public List<Message> getMessages(@PathVariable Long user1Id, @PathVariable Long user2Id) {
        return messageService.getMessages(user1Id, user2Id);
    }

    @PostMapping("/send")
    public void sendMessage(@RequestParam Long senderId, @RequestParam Long receiverId, @RequestParam String content) {
        messageService.sendMessage(senderId, receiverId, content);
    }
}
