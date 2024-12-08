package com.sash.volleyballApp.services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class EventReminderSchedulerService {

    @Scheduled(cron = "0 0 9 * * ?") // Example: Daily at 9 AM
    public void sendEventReminders() {
        // Logic to send reminders
    }
}
