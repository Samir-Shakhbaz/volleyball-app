package com.sash.volleyballApp.controllers;

import com.sash.volleyballApp.models.Event;
import com.sash.volleyballApp.models.PlayerProfile;
import com.sash.volleyballApp.models.User;
import com.sash.volleyballApp.services.EventService;
import com.sash.volleyballApp.services.FacilityService;
import com.sash.volleyballApp.services.PlayerProfileService;
import com.sash.volleyballApp.services.UserService;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PlayerProfileService playerProfileService;

    @Autowired
    private EventService eventService;

    @Autowired
    private FacilityService facilityService;

    @GetMapping("/dashboard")
    public String userDashboard(
            @RequestParam(required = false) String simpleLocation,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) Integer skillLevel,
            @RequestParam(required = false) String facility,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam(required = false) String genderFormat,
            @RequestParam(required = false) String eventName,
            @RequestParam(required = false) Integer teamSize,
            Model model
    ) {
        User user = userService.getLoggedInUser();

        // Use simpleLocation if present, otherwise proceed with advanced search
        Specification<Event> specification = (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            if (simpleLocation != null && !simpleLocation.isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("location"), simpleLocation));
            } else {
                if (location != null && !location.isEmpty()) {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("location"), location));
                }
                if (skillLevel != null) {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("skillLevel"), skillLevel));
                }
                if (facility != null && !facility.isEmpty()) {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("facility").get("name"), facility));
                }
                if (date != null) {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("date"), date));
                }
                if (genderFormat != null && !genderFormat.isEmpty()) {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("genderFormat"), genderFormat));
                }
                if (eventName != null && !eventName.isEmpty()) {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("name"), "%" + eventName + "%"));
                }
                if (teamSize != null) {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("teamSize"), teamSize));
                }
            }

            return predicate;
        };

        model.addAttribute("user", user);
        model.addAttribute("events", eventService.getAllEvents(specification));
        model.addAttribute("locations", eventService.getDistinctLocations());
        model.addAttribute("facilities", facilityService.getAllFacilities());
        model.addAttribute("eventNames", eventService.getDistinctEventNames());
        return "user-dashboard";
    }

    @PostMapping("/profile/update")
    public String updateProfile(@RequestBody PlayerProfile profile) {
        playerProfileService.updateProfile(profile);
        return "redirect:/user/dashboard";
    }
}
