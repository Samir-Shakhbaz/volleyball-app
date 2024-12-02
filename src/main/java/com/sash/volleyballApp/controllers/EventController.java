package com.sash.volleyballApp.controllers;

import com.sash.volleyballApp.models.Event;
import com.sash.volleyballApp.services.EventService;
import com.sash.volleyballApp.services.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private FacilityService facilityService;

    @GetMapping("/events/search")
    public String searchEvents(
            @RequestParam(required = false) String location,
            @RequestParam(required = false) Integer skillLevel,
            @RequestParam(required = false) String facilityName,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam(required = false) String genderFormat,  // Add missing parameter
            @RequestParam(required = false) String eventName,     // Add missing parameter
            Model model) {

        // Call the updated searchEvents method with all required parameters
        List<Event> events = eventService.searchEvents(location, skillLevel, facilityName, date, genderFormat, eventName);
        model.addAttribute("events", events);

        // Add other attributes for dropdowns or additional data
        model.addAttribute("facilities", facilityService.getAllFacilities());
        model.addAttribute("locations", eventService.getDistinctLocations());
        model.addAttribute("genderFormats", eventService.getDistinctGenderFormats()); // Add genderFormats
        model.addAttribute("eventNames", eventService.getDistinctEventNames());       // Add eventNames

        return "event-search"; // Replace with your actual template name
    }
}
