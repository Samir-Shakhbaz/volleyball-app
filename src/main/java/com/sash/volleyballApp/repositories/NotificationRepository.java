package com.sash.volleyballApp.repositories;

import com.sash.volleyballApp.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByUserIdAndSeenFalse(Long userId);

    List<Notification> findByUserId(Long userId);
}
