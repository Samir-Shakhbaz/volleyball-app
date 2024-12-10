package com.sash.volleyballApp.config;

import com.sash.volleyballApp.models.User;
import com.sash.volleyballApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminUserInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findByUsername("admin") == null) {
            User admin = new User();
            admin.setUsername("admin");
//            admin.setPassword(passwordEncoder.encode("admin")); // Encode the password
            admin.setPassword("admin");
            admin.setRole("ROLE_ADMIN"); // Assign the admin role
            admin.setEmail("admin@example.com");
            userRepository.save(admin);
            System.out.println("Admin user created with username: admin and password: admin");
        }
    }
}
