package com.sash.volleyballApp.services;

import com.sash.volleyballApp.models.Message;
import com.sash.volleyballApp.repositories.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatService {

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    public Message sendMessage(Long senderId, Long receiverId, String message) {
        Message chatMessage = new Message(null, senderId, receiverId, message, LocalDateTime.now());
        return chatMessageRepository.save(chatMessage);
    }

    public List<Message> getChatHistory(Long player1Id, Long player2Id) {
        return chatMessageRepository.findBySenderIdAndReceiverIdOrReceiverIdAndSenderId(
                player1Id, player2Id, player2Id, player1Id);
    }
}
