package com.sash.volleyballApp.controllersREST;

import com.sash.volleyballApp.models.Facility;
import com.sash.volleyballApp.services.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/facilities")
public class FacilityController {

    @Autowired
    private FacilityService facilityService;

    @GetMapping
    public ResponseEntity<List<Facility>> getAllFacilities() {
        List<Facility> facilities = facilityService.getAllFacilities();
        return ResponseEntity.ok(facilities);
    }

    @PostMapping
    public ResponseEntity<Facility> addFacility(@RequestBody Facility facility) {
        Facility savedFacility = facilityService.addFacility(facility);
        return ResponseEntity.ok(savedFacility);
    }
}

