package com.sash.volleyballApp.services;

import com.sash.volleyballApp.models.Message;
import com.sash.volleyballApp.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    // Fetch all public chat messages
    public List<Message> getAllMessages() {
        return messageRepository.findAllByOrderByTimestampAsc();
    }

    // Fetch private messages between two users
    public List<Message> getMessages(Long user1Id, Long user2Id) {
        return messageRepository.findBySenderIdAndReceiverIdOrReceiverIdAndSenderIdOrderByTimestamp(
                user1Id, user2Id, user2Id, user1Id
        );
    }

    // Save a new message (public or private)
    public void sendMessage(Long senderId, Long receiverId, String content) {
        Message message = new Message();
        message.setSenderId(senderId);
        message.setReceiverId(receiverId);
        message.setContent(content);
        message.setTimestamp(LocalDateTime.now()); // Set the current timestamp
        messageRepository.save(message);
    }

}
