package com.sash.volleyballApp.repositories;

import com.sash.volleyballApp.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findBySenderIdAndReceiverIdOrReceiverIdAndSenderIdOrderByTimestamp(
            Long senderId, Long receiverId, Long receiverId2, Long senderId2);

    List<Message> findAllByOrderByTimestampAsc(); // Fetch messages in chronological order

}
