package com.sash.volleyballApp.services;

import com.sash.volleyballApp.models.Availability;
import com.sash.volleyballApp.repositories.AvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AvailabilityService {

    @Autowired
    private AvailabilityRepository availabilityRepository;

    public Availability setOrUpdateAvailability(Availability availability) {
        Optional<Availability> existingAvailability = availabilityRepository.findByPlayerId(availability.getPlayerId());
        if (existingAvailability.isPresent()) {
            Availability updatedAvailability = existingAvailability.get();
            updatedAvailability.setWeekStart(availability.getWeekStart());
            updatedAvailability.setWeekEnd(availability.getWeekEnd());
            updatedAvailability.setAvailableTimes(availability.getAvailableTimes());
            return availabilityRepository.save(updatedAvailability);
        }
        return availabilityRepository.save(availability);
    }

    public Optional<Availability> getAvailabilityById(Long id) {
        return availabilityRepository.findById(id);
    }
}

