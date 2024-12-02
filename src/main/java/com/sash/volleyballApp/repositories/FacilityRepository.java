package com.sash.volleyballApp.repositories;

import com.sash.volleyballApp.models.Facility;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacilityRepository extends JpaRepository<Facility, Long> {
}
