package com.sash.volleyballApp.controllersREST;

import com.sash.volleyballApp.models.Availability;
import com.sash.volleyballApp.services.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/availability")
public class AvailabilityController {

    @Autowired
    private AvailabilityService availabilityService;

    @PostMapping
    public ResponseEntity<Availability> setOrUpdateAvailability(@RequestBody Availability availability) {
        Availability savedAvailability = availabilityService.setOrUpdateAvailability(availability);
        return ResponseEntity.ok(savedAvailability);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Availability> getAvailability(@PathVariable Long id) {
        Optional<Availability> availability = availabilityService.getAvailabilityById(id);
        return availability.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
