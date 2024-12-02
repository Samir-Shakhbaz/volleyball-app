package com.sash.volleyballApp.services;

import com.sash.volleyballApp.models.ChatMessage;
import com.sash.volleyballApp.repositories.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatService {

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    public ChatMessage sendMessage(Long senderId, Long receiverId, String message) {
        ChatMessage chatMessage = new ChatMessage(null, senderId, receiverId, message, LocalDateTime.now());
        return chatMessageRepository.save(chatMessage);
    }

    public List<ChatMessage> getChatHistory(Long player1Id, Long player2Id) {
        return chatMessageRepository.findBySenderIdAndReceiverIdOrReceiverIdAndSenderId(
                player1Id, player2Id, player2Id, player1Id);
    }
}
