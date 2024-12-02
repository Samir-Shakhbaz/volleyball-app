package com.sash.volleyballApp.services;

import com.sash.volleyballApp.models.RSVP;
import com.sash.volleyballApp.models.RSVP.Status;
import com.sash.volleyballApp.repositories.RSVPRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RSVPService {

    @Autowired
    private RSVPRepository rsvpRepository;

    public RSVP createOrUpdateRSVP(Long eventId, Long playerId, Status status) {
        RSVP rsvp = rsvpRepository.findByEventId(eventId).stream()
                .filter(r -> r.getPlayerId().equals(playerId))
                .findFirst()
                .orElse(new RSVP(null, eventId, playerId, status));
        rsvp.setStatus(status);
        return rsvpRepository.save(rsvp);
    }

    public List<RSVP> getRSVPsByEvent(Long eventId) {
        return rsvpRepository.findByEventId(eventId);
    }
}

