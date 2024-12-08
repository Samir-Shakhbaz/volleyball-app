package com.sash.volleyballApp.controllersREST;

import com.sash.volleyballApp.models.Notification;
import com.sash.volleyballApp.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationControllerREST {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/{userId}")
    public List<Notification> getNotifications(@PathVariable Long userId) {
        return notificationService.getUserNotifications(userId);
    }

    @PostMapping("/mark-as-seen/{notificationId}")
    public void markAsSeen(@PathVariable Long notificationId) {
        notificationService.markAsSeen(notificationId);
    }
}
