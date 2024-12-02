package com.sash.volleyballApp.repositories;

import com.sash.volleyballApp.models.RSVP;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RSVPRepository extends JpaRepository<RSVP, Long> {
    List<RSVP> findByEventId(Long eventId);

    List<RSVP> findByPlayerId(Long playerId);
}
