package com.sash.volleyballApp.repositories;

import com.sash.volleyballApp.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long>, JpaSpecificationExecutor<Event> {
    // JpaSpecificationExecutor allows filtering based on dynamic criteria

    // Find events by organizerId
    List<Event> findByOrganizerId(Long organizerId);
}
