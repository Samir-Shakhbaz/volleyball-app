package com.sash.volleyballApp.services;

import com.sash.volleyballApp.models.Event;
import com.sash.volleyballApp.models.PlayerProfile;
import com.sash.volleyballApp.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatchmakingService {

    @Autowired
    private EventRepository eventRepository;

    public List<Event> recommendEvents(PlayerProfile playerProfile) {
        return eventRepository.findAll().stream()
                .filter(event -> event.getSkillLevel() <= playerProfile.getSkillLevel() + 1 &&
                        event.getSkillLevel() >= playerProfile.getSkillLevel() - 1)
                .filter(event -> event.getLocation().equalsIgnoreCase(playerProfile.getBio())) // Assuming bio contains city info
                .filter(event -> event.getSurfaceType().equalsIgnoreCase("Sand")) // Example filter
                .collect(Collectors.toList());
    }
}
