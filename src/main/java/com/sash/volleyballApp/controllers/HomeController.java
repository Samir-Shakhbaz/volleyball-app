package com.sash.volleyballApp.controllers;

import com.sash.volleyballApp.models.Event;
import com.sash.volleyballApp.models.Facility;
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
public class HomeController {

    @Autowired
    private EventService eventService;

    @Autowired
    private FacilityService facilityService;

    @GetMapping("/")
    public String home(
            @RequestParam(required = false) String location,
            @RequestParam(required = false) Integer skillLevel,
            @RequestParam(required = false) String facility,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam(required = false) String genderFormat, // Add missing parameter
            @RequestParam(required = false) String eventName,    // Add missing parameter
            Model model) {

        // Fetch filtered events
        List<Event> filteredEvents = eventService.searchEvents(location, skillLevel, facility, date, genderFormat, eventName);

        // Fetch dropdown data
        List<String> locations = eventService.getDistinctLocations();
        List<Facility> facilities = facilityService.getAllFacilities();
        List<String> genderFormats = eventService.getDistinctGenderFormats(); // For gender dropdown
        List<String> eventNames = eventService.getDistinctEventNames();       // For event names dropdown

        // Add data to the model
        model.addAttribute("events", filteredEvents); // Filtered events
        model.addAttribute("locations", locations);   // For the locations dropdown
        model.addAttribute("facilities", facilities); // For the facilities dropdown
        model.addAttribute("genderFormats", genderFormats); // For the gender dropdown
        model.addAttribute("eventNames", eventNames);       // For the event names dropdown

        return "index"; // Render the index page
    }
}
