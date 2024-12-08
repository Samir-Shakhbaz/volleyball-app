package com.sash.volleyballApp.services;

import com.sash.volleyballApp.models.Notification;
import com.sash.volleyballApp.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private JavaMailSender mailSender;

    public void sendNotification(Long userId, String message, String type) {
        Notification notification = new Notification();
        notification.setUserId(userId);
        notification.setMessage(message);
        notification.setType(type);
        notification.setTimestamp(LocalDateTime.now());
        notification.setSeen(false);
        notificationRepository.save(notification);

        // Optionally send an email
        sendEmail(userId, message);
    }

    public List<Notification> getUserNotifications(Long userId) {
        return notificationRepository.findByUserId(userId);
    }

    public void markAsSeen(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId).orElseThrow();
        notification.setSeen(true);
        notificationRepository.save(notification);
    }

    private void sendEmail(Long userId, String message) {
        // Assume we retrieve the user's email by ID
        String userEmail = "user@example.com"; // Replace with actual email retrieval logic
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(userEmail);
        email.setSubject("New Notification");
        email.setText(message);
        mailSender.send(email);
    }
}
