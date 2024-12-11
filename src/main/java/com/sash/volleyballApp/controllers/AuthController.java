package com.sash.volleyballApp.controllers;

import com.sash.volleyballApp.models.PlayerProfile;

import com.sash.volleyballApp.models.User;
import com.sash.volleyballApp.services.UserService;
import com.sash.volleyballApp.services.PlayerProfileService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private PlayerProfileService playerProfileService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("playerProfile", new PlayerProfile());
        model.addAttribute("error", null); // Default error attribute
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username,
                               @RequestParam String email,
                               @RequestParam String password,
                               @RequestParam(defaultValue = "PLAYER") String role,
                               Model model) {
        try {
            userService.createUser(username, password, role, email);
            return "redirect:/login"; // Redirect to login on success
        } catch (Exception e) {
            model.addAttribute("error", "Registration failed. Try again.");
            return "register"; // Reload registration page on failure
        }
    }

    @PostMapping("/user")
    public String addUser(@ModelAttribute User user) {
        userService.createUser(
                user.getUsername(),
                user.getPassword(),
                user.getRole(),
                user.getEmail()
        );
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("error", null); // Default error attribute
        return "login";
    }

//    @PostMapping("/login")
//    public String login(@RequestParam String username,
//                        @RequestParam String password,
//                        Model model) {
//        try {
//            User user = userService.authenticate(username, password); // Add your login logic here
//            return "redirect:/"; // Redirect to the dashboard on success
//        } catch (IllegalArgumentException e) {
//            model.addAttribute("error", "Invalid username or password.");
//            return "login";
//        }
//    }

}
