package com.sash.volleyballApp.config;

import com.sash.volleyballApp.models.Event;
import com.sash.volleyballApp.models.Facility;
import com.sash.volleyballApp.repositories.EventRepository;
import com.sash.volleyballApp.repositories.FacilityRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class DataInitializer {

    @Autowired
    private FacilityRepository facilityRepository;

    @Autowired
    private EventRepository eventRepository;

    @PostConstruct
    public void initializeData() {
        if (facilityRepository.count() == 0 && eventRepository.count() == 0) {
            // Create facilities
            Facility sandFacility = facilityRepository.save(new Facility(null, "Sand Court A", "Los Angeles", "Sand", 2));
            Facility grassFacility = facilityRepository.save(new Facility(null, "Grass Field B", "Chicago", "Grass", 1));
            Facility indoorFacility = facilityRepository.save(new Facility(null, "Indoor Arena C", "New York", "Indoor", 3));

            Facility daleHaltonArena = facilityRepository.save(new Facility(null, "Dale F. Halton Arena", "Charlotte, NC", "Indoor", 5));
            Facility carolinaVolleyballCenter = facilityRepository.save(new Facility(null, "Carolina Volleyball Center", "Columbia, SC", "Indoor", 4));
            Facility northlakeSportsCenter = facilityRepository.save(new Facility(null, "Northlake Sports Center", "Charlotte, NC", "Indoor", 6));
            Facility sandboxSportsLink = facilityRepository.save(new Facility(null, "The Sandbox", "Charlotte, NC", "Sand", 3));
            Facility thirstySocialClub = facilityRepository.save(new Facility(null, "Thirsty Social Club", "Belmont, NC", "Sand", 2));
            Facility carolinaChaosVolleyball = facilityRepository.save(new Facility(null, "Carolina CHAOS Volleyball Club", "1281 Biscayne Dr., Concord, NC", "Indoor", 2));
            Facility abbeysAcademy = facilityRepository.save(new Facility(null, "Abbey's Academy Inc.", "Charlotte, NC", "Indoor", 1));
            Facility sportsConnection = facilityRepository.save(new Facility(null, "Sports Connection", "Charlotte, NC", "Indoor", 4));
            Facility alexanderStreetPark = facilityRepository.save(new Facility(null, "Alexander Street Park", "Charlotte, NC", "Outdoor", 1));
            Facility beattyPark = facilityRepository.save(new Facility(null, "Colonel Francis J. Beatty Park", "Charlotte, NC", "Outdoor", 2));
            Facility freedomPark = facilityRepository.save(new Facility(null, "Freedom Park", "Charlotte, NC", "Outdoor", 3));
            Facility hornetsNestPark = facilityRepository.save(new Facility(null, "Hornets Nest Park", "Charlotte, NC", "Outdoor", 2));

            // Create events and associate with facilities
            eventRepository.save(new Event(null, "Beach Volleyball Tournament", "Los Angeles", "Sand", 2, 5, "Coed",
                    LocalDate.of(2024, 12, 1), LocalTime.of(10, 0), 1L, sandFacility));
            eventRepository.save(new Event(null, "Grass League", "Chicago", "Grass", 6, 7, "Male",
                    LocalDate.of(2024, 12, 5), LocalTime.of(14, 0), 2L, grassFacility));
            eventRepository.save(new Event(null, "Indoor Pro Challenge", "New York", "Indoor", 4, 10, "Female",
                    LocalDate.of(2024, 12, 10), LocalTime.of(18, 0), 3L, indoorFacility));

            eventRepository.save(new Event(null, "UNC Volleyball Invitational", "Charlotte, NC", "Indoor", 6, 8, "Coed",
                    LocalDate.of(2024, 11, 20), LocalTime.of(17, 0), 4L, daleHaltonArena));
            eventRepository.save(new Event(null, "Gamecock Volleyball Championship", "Columbia, SC", "Indoor", 4, 7, "Female",
                    LocalDate.of(2024, 11, 15), LocalTime.of(16, 0), 5L, carolinaVolleyballCenter));
            eventRepository.save(new Event(null, "Northlake Winter League", "Charlotte, NC", "Indoor", 2, 6, "Male",
                    LocalDate.of(2025, 1, 10), LocalTime.of(10, 0), 6L, northlakeSportsCenter));
            eventRepository.save(new Event(null, "Sand Blast", "Charlotte, NC", "Sand", 3, 5, "Other",
                    LocalDate.of(2024, 12, 25), LocalTime.of(14, 0), 7L, sandboxSportsLink));
            eventRepository.save(new Event(null, "Beach Tournament", "Belmont, NC", "Sand", 2, 4, "Coed",
                    LocalDate.of(2024, 11, 30), LocalTime.of(12, 0), 8L, thirstySocialClub));

            eventRepository.save(new Event(null, "Chaos Indoor Cup", "Concord, NC", "Indoor", 3, 7, "Female",
                    LocalDate.of(2024, 12, 8), LocalTime.of(15, 0), 9L, carolinaChaosVolleyball));
            eventRepository.save(new Event(null, "Abbey's Invitational", "Charlotte, NC", "Indoor", 4, 5, "Male",
                    LocalDate.of(2024, 12, 18), LocalTime.of(11, 0), 10L, abbeysAcademy));
            eventRepository.save(new Event(null, "Sports Connection Challenge", "Charlotte, NC", "Indoor", 5, 8, "Coed",
                    LocalDate.of(2024, 12, 22), LocalTime.of(13, 0), 11L, sportsConnection));
            eventRepository.save(new Event(null, "Alexander Park Open", "Charlotte, NC", "Outdoor", 2, 6, "Other",
                    LocalDate.of(2024, 11, 10), LocalTime.of(14, 0), 12L, alexanderStreetPark));
            eventRepository.save(new Event(null, "Beatty Outdoor League", "Charlotte, NC", "Outdoor", 3, 7, "Female",
                    LocalDate.of(2024, 11, 25), LocalTime.of(10, 0), 13L, beattyPark));
            eventRepository.save(new Event(null, "Freedom Outdoor Challenge", "Charlotte, NC", "Outdoor", 4, 9, "Male",
                    LocalDate.of(2024, 11, 29), LocalTime.of(16, 0), 14L, freedomPark));
            eventRepository.save(new Event(null, "Hornets Nest Sand Bash", "Charlotte, NC", "Outdoor", 2, 5, "Coed",
                    LocalDate.of(2024, 12, 3), LocalTime.of(12, 0), 15L, hornetsNestPark));
        }
    }
}

