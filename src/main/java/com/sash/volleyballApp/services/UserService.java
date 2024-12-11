package com.sash.volleyballApp.services;

import com.sash.volleyballApp.models.Event;
import com.sash.volleyballApp.models.PlayerProfile;
import com.sash.volleyballApp.models.User;
import com.sash.volleyballApp.repositories.EventRepository;
import com.sash.volleyballApp.repositories.PlayerProfileRepository;
import com.sash.volleyballApp.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

    public List<Event> getUserEvents(Long userId) {
        // Assuming we have a field in Event to associate with users (e.g., organizerId or participantIds)
        return eventRepository.findByOrganizerId(userId); // Example query
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

//    public User createUser(User user) {
//        return userRepository.save(user);
//    }

    public void createUser(String username, String password, String role, String email) {
        System.out.println("createUser called with: " + username + ", " + email + ", " + role);
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password)); // Encrypt the password
        user.setRole(role);
        user.setEmail(email);
        userRepository.save(user); // Persist the user
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

//        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);

        // Link the PlayerProfile to the User
        playerProfile.setUser(user);

        playerProfileRepository.save(playerProfile);
    }

    public User authenticate(String username, String password) {
        User user = userRepository.findByUsername(username);
//        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
//            throw new IllegalArgumentException("Invalid username or password");
//        }
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}

