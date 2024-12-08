package com.sash.volleyballApp.controllers;

import com.sash.volleyballApp.models.Event;
import com.sash.volleyballApp.models.Facility;
import com.sash.volleyballApp.services.EventService;
import com.sash.volleyballApp.services.FacilityService;
import com.sash.volleyballApp.api.WeatherAPIClient;
import com.sash.volleyballApp.api.WeatherAPIResponse;
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

    @Autowired
    private WeatherAPIClient weatherAPIClient; // Inject WeatherAPIClient

    @GetMapping("/")
    public String home(
            @RequestParam(required = false) String location,
            @RequestParam(required = false) Integer skillLevel,
            @RequestParam(required = false) String facility,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam(required = false) String genderFormat,
            @RequestParam(required = false) String eventName,
            Model model) {

        // Fetch filtered events
        List<Event> filteredEvents = eventService.searchEvents(location, skillLevel, facility, date, genderFormat, eventName);

        // Fetch dropdown data
        List<String> locations = eventService.getDistinctLocations();
        List<Facility> facilities = facilityService.getAllFacilities();
        List<String> genderFormats = eventService.getDistinctGenderFormats();
        List<String> eventNames = eventService.getDistinctEventNames();

        // Fetch weather data for Charlotte
        String defaultCity = "Charlotte"; // Set default city here
        WeatherAPIResponse weatherResponse = weatherAPIClient.getWeather(defaultCity);

        // Format the weather information
//        String weatherInfo = String.format("Temperature: %s, Wind: %s, Description: %s",
//                weatherResponse.getTemperature(),
//                weatherResponse.getWind(),
//                weatherResponse.getDescription());

        // Add data to the model
        model.addAttribute("events", filteredEvents);
        model.addAttribute("locations", locations);
        model.addAttribute("facilities", facilities);
        model.addAttribute("genderFormats", genderFormats);
        model.addAttribute("eventNames", eventNames);
//        model.addAttribute("weather", weatherInfo); // Add weather info to the model

        return "index"; // Render the index page
    }
}

