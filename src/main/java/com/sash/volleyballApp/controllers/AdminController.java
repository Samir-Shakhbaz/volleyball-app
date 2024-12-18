package com.sash.volleyballApp.controllers;

import com.sash.volleyballApp.models.Facility;
import com.sash.volleyballApp.models.Event;
import com.sash.volleyballApp.models.User;
import com.sash.volleyballApp.services.FacilityService;
import com.sash.volleyballApp.services.EventService;
import com.sash.volleyballApp.services.UserService;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private FacilityService facilityService;

    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

//    @GetMapping("/dashboard")
//    public String adminDashboard(Model model) {
//        model.addAttribute("facilities", facilityService.getAllFacilities());
//        model.addAttribute("events", eventService.getAllEvents());
//        return "admin/dashboard";
//    }

    @GetMapping("/dashboard")
    public String adminDashboard(
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String surfaceType,
            @RequestParam(required = false) Integer skillLevel,
            Model model) {

        // Build Specification dynamically based on request parameters
        Specification<Event> specification = (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction(); // Default to true
            if (location != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("location"), location));
            }
            if (surfaceType != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("surfaceType"), surfaceType));
            }
            if (skillLevel != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("skillLevel"), skillLevel));
            }
            return predicate;
        };

        // Pass dropdown data to the model
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("roles", List.of("ADMIN", "PLAYER", "ORGANIZER")); // Dynamic roles
        model.addAttribute("locations", List.of("New York", "Los Angeles", "Chicago")); // Example locations
        model.addAttribute("surfaceTypes", List.of("Sand", "Grass", "Indoor")); // Example surface types
        model.addAttribute("events", eventService.getAllEvents(specification));
        model.addAttribute("facilities", facilityService.getAllFacilities());
        model.addAttribute("eventStats", eventService.getEventStats());

        // Fetch and add new facilities to the model
        List<Facility> newFacilities = facilityService.getAllFacilities();
        model.addAttribute("newFacilities", newFacilities);

        return "admin-dashboard";
    }

    @PostMapping("/facility")
    public String addFacility(Facility facility) {
        facilityService.addFacility(facility);
        return "redirect:/admin/dashboard";
    }

//    @PostMapping("/event")
//    public String addEvent(Event event) {
//        eventService.createEvent(event);
//        return "redirect:/admin/dashboard";
//    }

//    @PostMapping("/event")
//    public String addEvent(@ModelAttribute Event event, @RequestParam Long facilityId, Model model) {
//        // Retrieve the existing facility by ID
//        Facility facility = facilityService.getFacilityById(facilityId);
//
//        // Associate the facility with the event
//        event.setFacility(facility);
//
//        // Save the event
//        eventService.createEvent(event);
//
//        return "redirect:/admin/dashboard";
//    }

    @PostMapping("/event")
    public String addEvent(
            @RequestParam String name,
            @RequestParam String location,
            @RequestParam(required = false) String newLocationName,
            @RequestParam String surfaceType,
            @RequestParam int teamSize,
            @RequestParam int skillLevel,
            @RequestParam String genderFormat,
            @RequestParam String date,
            @RequestParam String time,
            @RequestParam String facilityId,
            @RequestParam(required = false) String newFacilityName,
            Model model) {

        // Handle new location
        if ("new".equals(location)) {
            if (newLocationName == null || newLocationName.isBlank()) {
                model.addAttribute("error", "New location name is required.");
                return "admin-dashboard";
            }
            location = newLocationName; // Use the new location name
        }

        // Handle new facility
        Facility facility;
        if ("new".equals(facilityId)) {
            if (newFacilityName == null || newFacilityName.isBlank()) {
                model.addAttribute("error", "New facility name is required.");
                return "admin-dashboard";
            }
            facility = new Facility();
            facility.setName(newFacilityName);
            facility.setLocation(location);
            facility.setSurfaceType(surfaceType);
            facility.setCourts(teamSize);
            facility = facilityService.addFacility(facility); // Persist new facility
        } else {
            facility = facilityService.getFacilityById(Long.parseLong(facilityId)); // Parse facilityId if existing
        }

        // Create Event
        Event event = new Event();
        event.setName(name);
        event.setLocation(location);
        event.setSurfaceType(surfaceType);
        event.setTeamSize(teamSize);
        event.setSkillLevel(skillLevel);
        event.setGenderFormat(genderFormat);
        event.setDate(LocalDate.parse(date)); // Convert String to LocalDate
        event.setTime(LocalTime.parse(time)); // Convert String to LocalTime
        event.setFacility(facility);

        eventService.createEvent(event);

        return "redirect:/admin/dashboard";
    }

//    @PostMapping("/user")
//    public String addUser(User user) {
//        userService.createUser(user);
//        return "redirect:/admin/dashboard";
//    }

    @PostMapping("/user")
    public String addUser(@ModelAttribute User user) {
        userService.createUser(
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getGender(),
                user.getHomeCity()
        );
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/user/edit/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "edit-user";
    }

    @PostMapping("/user/update")
    public String updateUser(@ModelAttribute User user) {
        userService.updateUser(user);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/user/search")
    public String searchUsers(@RequestParam String query, Model model) {
        List<User> users = userService.searchUsers(query);
        model.addAttribute("users", users);
        return "admin-dashboard";
    }

}
