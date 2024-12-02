package com.sash.volleyballApp.services;


import com.sash.volleyballApp.models.PlayerProfile;
import com.sash.volleyballApp.repositories.PlayerProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayerProfileService {

    @Autowired
    private PlayerProfileRepository playerProfileRepository;

    public Optional<PlayerProfile> getProfileById(Long id) {
        return playerProfileRepository.findById(id);
    }

    public PlayerProfile createOrUpdateProfile(PlayerProfile profile) {
        Optional<PlayerProfile> existingProfile = playerProfileRepository.findByUserId(profile.getUser().getId());
        if (existingProfile.isPresent()) {
            PlayerProfile updatedProfile = existingProfile.get();
            updatedProfile.setBio(profile.getBio());
            updatedProfile.setGender(profile.getGender());
            updatedProfile.setHeight(profile.getHeight());
            updatedProfile.setSkillLevel(profile.getSkillLevel());
            updatedProfile.setMotivation(profile.getMotivation());
            return playerProfileRepository.save(updatedProfile);
        }
        return playerProfileRepository.save(profile);
    }

    // Fetch profile by user ID
    public PlayerProfile getProfileByUserId(Long userId) {
        return playerProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Profile not found for user ID " + userId));
    }

    // Update profile
    public PlayerProfile updateProfile(PlayerProfile profile) {
        if (profile.getId() == null) {
            throw new IllegalArgumentException("Profile ID cannot be null");
        }
        return playerProfileRepository.save(profile);
    }

    public void savePlayerProfile(PlayerProfile playerProfile) {
        playerProfileRepository.save(playerProfile);
    }

}