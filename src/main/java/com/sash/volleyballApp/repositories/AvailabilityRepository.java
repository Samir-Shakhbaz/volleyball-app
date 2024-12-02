package com.sash.volleyballApp.repositories;

import com.sash.volleyballApp.models.Availability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AvailabilityRepository extends JpaRepository<Availability, Long> {
    Optional<Availability> findByPlayerId(Long playerId);
}
