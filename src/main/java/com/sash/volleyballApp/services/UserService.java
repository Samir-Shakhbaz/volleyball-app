package com.sash.volleyballApp.services;

import com.sash.volleyballApp.models.Event;
import com.sash.volleyballApp.models.PlayerProfile;
import com.sash.volleyballApp.models.User;
import com.sash.volleyballApp.repositories.EventRepository;
import com.sash.volleyballApp.repositories.PlayerProfileRepository;
import com.sash.volleyballApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private PlayerProfileRepository playerProfileRepository;

    public User getLoggedInUser() {
        // Placeholder logic for demo; replace with actual authentication context
        return userRepository.findById(1L).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    // Fetch events for a specific user
    public List<Event> getUserEvents(Long userId) {
        // Assuming you have a field in Event to associate with users (e.g., organizerId or participantIds)
        return eventRepository.findByOrganizerId(userId); // Example query
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> searchUsers(String query) {
        return userRepository.findByUsernameContainingIgnoreCase(query);
    }



    public void registerUserWithProfile(User user, PlayerProfile playerProfile) {
        // Check if the username or email already exists
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new IllegalArgumentException("Username is already taken");
        }

        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new IllegalArgumentException("Email is already in use");
        }

        // Encrypt the password
//        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Save the user
        userRepository.save(user);

        // Link the PlayerProfile to the User
        playerProfile.setUser(user);

        // Save the PlayerProfile
        playerProfileRepository.save(playerProfile);
    }

    public User authenticate(String username, String password) {
        User user = userRepository.findByUsername(username);
//        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
//            throw new IllegalArgumentException("Invalid username or password");
//        }
        return user;
    }

}

