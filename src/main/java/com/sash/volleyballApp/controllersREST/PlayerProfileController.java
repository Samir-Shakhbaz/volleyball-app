package com.sash.volleyballApp.controllersREST;

import com.sash.volleyballApp.models.PlayerProfile;
import com.sash.volleyballApp.services.PlayerProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/profiles")
public class PlayerProfileController {

    @Autowired
    private PlayerProfileService playerProfileService;

    @GetMapping("/{id}")
    public ResponseEntity<PlayerProfile> getProfile(@PathVariable Long id) {
        Optional<PlayerProfile> profile = playerProfileService.getProfileById(id);
        return profile.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PlayerProfile> createOrUpdateProfile(@RequestBody PlayerProfile profile) {
        PlayerProfile savedProfile = playerProfileService.createOrUpdateProfile(profile);
        return ResponseEntity.ok(savedProfile);
    }
}
