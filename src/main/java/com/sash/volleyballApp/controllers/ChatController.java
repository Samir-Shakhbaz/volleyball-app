package com.sash.volleyballApp.controllers;

import com.sash.volleyballApp.models.Message;
import com.sash.volleyballApp.models.User;
import com.sash.volleyballApp.services.MessageService;
import com.sash.volleyballApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ChatController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    // Serve the public chat page
    @GetMapping("/public-chat")
    public String publicChat() {
        return "public-chat"; // Renders public-chat.html from templates
    }

    // Serve the private chat page
    @GetMapping("/private-chat")
    public String privateChat(Principal principal, Model model) {
        String username = principal.getName(); // Get the logged-in username
        User user = userService.findByUsername(username); // Fetch user details from the database
        model.addAttribute("loggedInUser", user); // Pass the user to the frontend
        return "private-chat";
    }


    // Fetch public chat history
    @GetMapping("/api/messages")
    @ResponseBody
    public List<Message> getPublicMessages() {
        return messageService.getAllMessages(); // Retrieve all public chat messages
    }

    // Fetch private chat history between two users
    @GetMapping("/api/private-messages")
    @ResponseBody
    public List<Message> getPrivateMessages(@RequestParam Long user1Id, @RequestParam Long user2Id) {
        return messageService.getMessages(user1Id, user2Id); // Retrieve private messages
    }

    // Handle public chat messages
    @MessageMapping("/sendMessage")
    @SendTo("/topic/public")
    public Message handlePublicChat(String content) {
        Message message = new Message();
        message.setSenderId(0L); // Public chat has no specific sender
        message.setReceiverId(0L); // Public chat has no specific receiver
        message.setContent(content);
        message.setTimestamp(LocalDateTime.now()); // Set the current timestamp
        messageService.sendMessage(message.getSenderId(), message.getReceiverId(), message.getContent());
        return message;
    }


    @MessageMapping("/private-message")
    @SendTo("/queue/private")
    public Message handlePrivateChat(Message message) {
        System.out.println("Received message: " + message);
        System.out.println("Sender ID: " + message.getSenderId());
        System.out.println("Receiver ID: " + message.getReceiverId());
        System.out.println("Content: " + message.getContent());

        if (message.getSenderId() == null || message.getReceiverId() == null || message.getContent() == null) {
            throw new IllegalArgumentException("Invalid message data");
        }

        messageService.sendMessage(message.getSenderId(), message.getReceiverId(), message.getContent());
        return message;
    }
}
