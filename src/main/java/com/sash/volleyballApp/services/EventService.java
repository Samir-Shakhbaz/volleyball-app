package com.sash.volleyballApp.services;

import com.sash.volleyballApp.models.Event;
import com.sash.volleyballApp.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll(); // Fetch all events without filtering
    }

    public List<Event> getAllEvents(Specification<Event> specification) {
        return eventRepository.findAll(specification);
    }

    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }

    public Map<String, Long> getEventStats() {
        return eventRepository.findAll().stream()
                .collect(Collectors.groupingBy(Event::getName, Collectors.counting()));
    }

    public List<String> getDistinctLocations() {
        return eventRepository.findAll().stream()
                .map(Event::getLocation) // Extract the location field
                .distinct()             // Ensure uniqueness
                .toList();
    }

    public List<String> getDistinctGenderFormats() {
        return eventRepository.findAll().stream()
                .map(Event::getGenderFormat) // Extract gender formats
                .filter(genderFormat -> genderFormat != null && !genderFormat.isEmpty()) // Avoid nulls or empty strings
                .distinct()                  // Remove duplicates
                .toList();
    }

    public List<String> getDistinctEventNames() {
        return eventRepository.findAll().stream()
                .map(Event::getName) // Extract event names
                .filter(eventName -> eventName != null && !eventName.isEmpty()) // Avoid nulls or empty strings
                .distinct()          // Remove duplicates
                .toList();
    }

    public List<Event> searchEvents(
            String location,
            Integer skillLevel,
            String facilityName,
            LocalDate date,
            String genderFormat,
            String eventName
    ) {
        Specification<Event> spec = (root, query, criteriaBuilder) -> {
            var predicate = criteriaBuilder.conjunction();

            if (location != null && !location.isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("location"), location));
            }
            if (skillLevel != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("skillLevel"), skillLevel));
            }
            if (facilityName != null && !facilityName.isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("facility").get("name"), facilityName));
            }
            if (date != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("date"), date));
            }
            if (genderFormat != null && !genderFormat.isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("genderFormat"), genderFormat));
            }
            if (eventName != null && !eventName.isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("name"), "%" + eventName + "%"));
            }

            return predicate;
        };

        return eventRepository.findAll(spec);
    }
}
