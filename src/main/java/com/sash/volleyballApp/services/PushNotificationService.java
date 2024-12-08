package com.sash.volleyballApp.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


@Service
public class PushNotificationService {

    private final String FCM_URL = "https://fcm.googleapis.com/fcm/send";

    //TODO: WE NEED TO GET OUR OWN KEY
    private final String SERVER_KEY = "our-firebase-server-key"; // WE NEED TO GET OUR OWN KEY

    public void sendPushNotification(String token, String title, String body) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + SERVER_KEY);
        headers.set("Content-Type", "application/json");

        Map<String, Object> notification = new HashMap<>();
        notification.put("title", title);
        notification.put("body", body);

        Map<String, Object> bodyMap = new HashMap<>();
        bodyMap.put("to", token);
        bodyMap.put("notification", notification);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(bodyMap, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(FCM_URL, entity, String.class);
        System.out.println("FCM Response: " + response.getBody());
    }
}
