package com.sash.volleyballApp.controllersREST;

import com.sash.volleyballApp.models.Event;
import com.sash.volleyballApp.services.EventService;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/events")
public class EventControllerRest {

    @Autowired
    private EventService eventService;

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        Event savedEvent = eventService.createEvent(event);
        return ResponseEntity.ok(savedEvent);
    }

    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents(
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String surfaceType,
            @RequestParam(required = false) Integer skillLevel,
            @RequestParam(required = false) String genderFormat) {

        Specification<Event> specification = (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            if (location != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("location"), location));
            }
            if (surfaceType != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("surfaceType"), surfaceType));
            }
            if (skillLevel != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("skillLevel"), skillLevel));
            }
            if (genderFormat != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("genderFormat"), genderFormat));
            }
            return predicate;
        };

        List<Event> events = eventService.getAllEvents(specification);
        return ResponseEntity.ok(events);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        Optional<Event> event = eventService.getEventById(id);
        return event.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
