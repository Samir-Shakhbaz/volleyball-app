package com.sash.volleyballApp.services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Scheduled(cron = "0 0 9 * * SUN") // Every Sunday at 9:00 AM
    public void sendWeeklyAvailabilityReminder() {
        // Logic to send notifications (e.g., email, push notifications)
        System.out.println("Weekly reminder sent to all players to update availability!");
    }
}
