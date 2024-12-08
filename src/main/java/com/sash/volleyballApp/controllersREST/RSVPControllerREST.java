package com.sash.volleyballApp.controllersREST;

import com.sash.volleyballApp.models.RSVP;
import com.sash.volleyballApp.models.RSVP.Status;
import com.sash.volleyballApp.services.RSVPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class RSVPControllerREST {

    @Autowired
    private RSVPService rsvpService;

    @PostMapping("/{id}/rsvp")
    public ResponseEntity<RSVP> createRSVP(
            @PathVariable Long id,
            @RequestParam Long playerId,
            @RequestParam Status status) {
        RSVP rsvp = rsvpService.createOrUpdateRSVP(id, playerId, status);
        return ResponseEntity.ok(rsvp);
    }

    @PatchMapping("/{id}/rsvp")
    public ResponseEntity<RSVP> updateRSVP(
            @PathVariable Long id,
            @RequestParam Long playerId,
            @RequestParam Status status) {
        RSVP rsvp = rsvpService.createOrUpdateRSVP(id, playerId, status);
        return ResponseEntity.ok(rsvp);
    }

    @GetMapping("/{id}/rsvps")
    public ResponseEntity<List<RSVP>> getRSVPsForEvent(@PathVariable Long id) {
        List<RSVP> rsvps = rsvpService.getRSVPsByEvent(id);
        return ResponseEntity.ok(rsvps);
    }
}

